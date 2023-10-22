package com.evigel.sunnytable.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.evigel.sunnytable.dto.FileDto;
import com.evigel.sunnytable.entity.Foid;
import com.evigel.sunnytable.entity.Udid;
import com.evigel.sunnytable.entity.Ufid;
import com.evigel.sunnytable.entity.Umid;
import com.evigel.sunnytable.entity.Upid;
import com.evigel.sunnytable.entity.Utid;
import com.evigel.sunnytable.exception.MyException;
import com.evigel.sunnytable.exception.ResultEnum;
import com.evigel.sunnytable.mapper.FoidMapper;
import com.evigel.sunnytable.mapper.UdidMapper;
import com.evigel.sunnytable.mapper.UfidMapper;
import com.evigel.sunnytable.mapper.UmidMapper;
import com.evigel.sunnytable.mapper.UpidMapper;
import com.evigel.sunnytable.mapper.UtidMapper;
import com.evigel.sunnytable.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.lang.reflect.Type;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author evigel
 * @since 2021-04-28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FileServiceImpl implements IFileService {

    @Autowired
    private UpidMapper upidMapper;

    @Autowired
    private UtidMapper utidMapper;

    @Autowired
    private UmidMapper umidMapper;

    @Autowired
    private UdidMapper udidMapper;

    @Autowired
    private UfidMapper ufidMapper;

    @Autowired
    private FoidMapper foidMapper;

    @Override
    public List<FileDto> openDir(String uid, long did) {
        Udid udidroot = new Udid();
        udidroot.setUid(uid);
        udidroot.setType(4);
        QueryWrapper<Udid> wrapper4 = new QueryWrapper<>(udidroot);
        Udid udid = udidMapper.selectOne(wrapper4);

        if (udid == null) {
            throw new MyException(ResultEnum.DATA_ERROR, "用户id(uid)不存在");
        }

        Foid foidroot = new Foid();
        foidroot.setOid(udid.getDid());
        QueryWrapper<Foid> wrapper5 = new QueryWrapper<>(foidroot);
        Foid foidr = foidMapper.selectOne(wrapper5);
        long fidroot = foidr.getFid();

        Foid foidNew = new Foid();
        foidNew.setFid(did);
        QueryWrapper<Foid> wrapper = new QueryWrapper<>(foidNew);
        Foid foid = foidMapper.selectOne(wrapper);

        if (foid == null) {
            throw new MyException(ResultEnum.DATA_ERROR, "文件夹id(fid)不存在");
        }
        long fatherId = foid.getOid();
        QueryWrapper<Ufid> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("did", fatherId);
        List<Ufid> ufids = ufidMapper.selectList(wrapper2);
        List<FileDto> list = new ArrayList<>();
        for (int i = 0; i < ufids.size(); i++) {
            Ufid ufid = ufids.get(i);
            if (ufid.getIsVisible() != null && ufid.getIsVisible().equals("false")) {
                continue;
            }
            long id = ufid.getFid();
            if (id == fidroot) {
                continue;
            }
            String name = ufid.getFname();

            Foid foidNew2 = new Foid();
            foidNew2.setFid(id);
            QueryWrapper<Foid> wrapper3 = new QueryWrapper<>(foidNew2);
            Foid foidOne = foidMapper.selectOne(wrapper3);

            int type = foidOne.getType();
            int templateType = 0;
            if (type == 3) {
                QueryWrapper<Umid> typeWrapper = new QueryWrapper<>();
                typeWrapper.eq("mid", foidOne.getOid());
                Umid umid = umidMapper.selectOne(typeWrapper);
                templateType = umid.getType();
            } else if (type == 1) {
                System.out.println(foidOne.getOid());
                List<Integer> types = upidMapper.getType(foidOne.getOid());
                templateType = types.get(0);
            } else if (type == 4) {
                QueryWrapper<Udid> typeWrapper = new QueryWrapper<>();
                typeWrapper.eq("did", foidOne.getOid());
                Udid udidQuery = udidMapper.selectOne(typeWrapper);
                templateType = udidQuery.getType();
            }
            Timestamp createTime = foidOne.getCreateTime();
            //String time = createTime.toString();
            //createTime.setTime(createTime-Timestamp.valueOf("2021-05-14 16:01:27"));
            //System.out.println(createTime);
            FileDto file = new FileDto(id, name, type, templateType, createTime);
            list.add(file);
        }
        return list;
    }

    @Override
    public Long createRoot(String userId) {
        /*Foid ffoid = foidMapper.selectById(dirId);
        long fatherDid = ffoid.getOid();*/

        Udid udid = new Udid();
        udid.setUid(userId);
        udid.setDirName("root");
        udid.setType(4);
        udidMapper.insert(udid);

        long did = udid.getDid();

        Ufid ufid = new Ufid();
        ufid.setUid(userId);
        ufid.setDid(did);
        ufid.setFname("root");

        ufidMapper.insert(ufid);

        long fid = ufid.getFid();
        Foid foid = new Foid();
        foid.setFid(fid);
        foid.setType(4);
        //foid.setCreateTime(LocalDateTime.now());
        //foid.setModifyTime(LocalDateTime.now());
        foid.setOid(did);
        foidMapper.insert(foid);

        // TODO
        createDefaultDir(userId, fid, "图", 1);
        createDefaultDir(userId, fid, "表", 3);
        createDefaultDir(userId, fid, "模板", 2);
        return fid;
    }


    public Long createDefaultDir(String userId, long dirId, String name, int dirType) {
        /*

        wrapper.eq("did", dirId);
        Foid ffoid = foidMapper.selectOne(wrapper);*/
        Foid foidNew = new Foid();
        foidNew.setFid(dirId);
        QueryWrapper<Foid> wrapper = new QueryWrapper<>(foidNew);
        Foid ffoid = foidMapper.selectOne(wrapper);
        long fatherDid = ffoid.getOid();

        Udid udid = new Udid();
        udid.setUid(userId);
        udid.setDirName(name);
        udid.setType(dirType);
        udidMapper.insert(udid);

        long did = udid.getDid();

        Ufid ufid = new Ufid();
        ufid.setUid(userId);
        ufid.setDid(fatherDid);
        ufid.setFname(name);
        ufidMapper.insert(ufid);

        long fid = ufid.getFid();
        Foid foid = new Foid();
        foid.setFid(fid);
        foid.setType(4);
        //foid.setCreateTime(LocalDateTime.now());
        //foid.setModifyTime(LocalDateTime.now());
        foid.setOid(did);
        foidMapper.insert(foid);
        return fid;
    }


    @Override
    public FileDto createDir(String userId, long dirId, String name) {
        Foid foidNew = new Foid();
        foidNew.setFid(dirId);
        QueryWrapper<Foid> wrapper = new QueryWrapper<>(foidNew);
        Foid ffoid = foidMapper.selectOne(wrapper);
        if (ffoid == null) {
            throw new MyException(ResultEnum.DATA_ERROR, "文件夹id(fid)不存在");
        }

        long fatherDid = ffoid.getOid();
        Udid udid = new Udid();
        udid.setUid(userId);
        udid.setDirName(name);
        udid.setType(0);
        udidMapper.insert(udid);

        long did = udid.getDid();

        Ufid ufid = new Ufid();
        ufid.setUid(userId);
        ufid.setDid(fatherDid);
        ufid.setFname(name);
        ufidMapper.insert(ufid);

        long fid = ufid.getFid();
        Foid foid = new Foid();
        foid.setFid(fid);
        foid.setType(4);
        //foid.setCreateTime(LocalDateTime.now());
        //foid.setModifyTime(LocalDateTime.now());
        foid.setOid(did);
        foidMapper.insert(foid);

        FileDto file = new FileDto(fid, name, 4, 0, foid.getCreateTime());
        return file;
    }

    @Override
    public void move(long srcFid, long dstFid) {
        Foid foidNew = new Foid();
        foidNew.setFid(dstFid);
        QueryWrapper<Foid> wrapper2 = new QueryWrapper<>(foidNew);
        Foid ffoid = foidMapper.selectOne(wrapper2);
        if (ffoid == null) {
            throw new MyException(ResultEnum.DATA_ERROR, "目标文件夹id(fid)不存在");
        }
        long fatherDid = ffoid.getOid();
        Ufid ufid = new Ufid();
        ufid.setDid(fatherDid);
        UpdateWrapper<Ufid> wrapper = new UpdateWrapper<>();
        wrapper.eq("fid", srcFid);
        ufidMapper.update(ufid, wrapper);
    }

    public void removeTemplate(long mid) {
        QueryWrapper<Umid> wrapper = new QueryWrapper<>();
        wrapper.eq("mid", mid);
        umidMapper.delete(wrapper);
    }

    public void removeData(long tid) {
        QueryWrapper<Utid> wrapper = new QueryWrapper<>();
        wrapper.eq("tid", tid);
        utidMapper.delete(wrapper);
    }

    @Override
    public void remove(long srcFid) {
        QueryWrapper<Ufid> wrapper = new QueryWrapper<>();
        wrapper.eq("fid", srcFid);
        ufidMapper.delete(wrapper);
        Foid foid = new Foid();
        foid.setFid(srcFid);
        QueryWrapper<Foid> wrapper2 = new QueryWrapper<>(foid);
        Foid foidDe = foidMapper.selectOne(wrapper2);

        int type = foidDe.getType();
        if (type == 3) {
            removeTemplate(foidDe.getOid());
        } else if (type == 1) {
            long pid = foidDe.getOid();
            QueryWrapper<Upid> wrapper3 = new QueryWrapper<>();
            wrapper3.eq("pid", pid);
            Upid upid = upidMapper.selectOne(wrapper3);
            removeTemplate(upid.getMid());
            removeData(upid.getTid());
            upidMapper.delete(wrapper3);
        } else if (type == 2) {
            removeData(foidDe.getOid());
        }

    }

    @Override
    public void rename(long srcFid, String srcName) {
        Ufid ufid = new Ufid();
        ufid.setFname(srcName);
        UpdateWrapper<Ufid> wrapper = new UpdateWrapper<>();
        wrapper.eq("fid", srcFid);

        ufidMapper.update(ufid, wrapper);

        Foid foidNew = new Foid();
        foidNew.setFid(srcFid);
        QueryWrapper<Foid> wrapper2 = new QueryWrapper<>(foidNew);
        Foid foid = foidMapper.selectOne(wrapper2);
        if (foid == null) {
            throw new MyException(ResultEnum.DATA_ERROR, "文件id(fid)不存在");

        }
        long type = foid.getType();
        long oid = foid.getOid();
        if (type == 1) {
            Upid upid = new Upid();
            upid.setPName(srcName);
            UpdateWrapper<Upid> wrapper3 = new UpdateWrapper<>();
            wrapper3.eq("pid", oid);
            upidMapper.update(upid, wrapper3);
        } else if (type == 2) {
            Utid utid = new Utid();
            utid.setTn(srcName);
            UpdateWrapper<Utid> wrapper3 = new UpdateWrapper<>();
            wrapper3.eq("tid", oid);
            utidMapper.update(utid, wrapper3);
        } else if (type == 3) {
            Umid umid = new Umid();
            umid.setMn(srcName);
            UpdateWrapper<Umid> wrapper3 = new UpdateWrapper<>();
            wrapper3.eq("mid", oid);
            umidMapper.update(umid, wrapper3);
        } else if (type == 4) {
            Udid udid = new Udid();
            udid.setDirName(srcName);
            UpdateWrapper<Udid> wrapper3 = new UpdateWrapper<>();
            wrapper3.eq("did", oid);
            udidMapper.update(udid, wrapper3);
        }
    }

}
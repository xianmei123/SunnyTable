package com.evigel.sunnytable.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.evigel.sunnytable.dto.DataSetDto;
import com.evigel.sunnytable.dto.LineDataDto;
import com.evigel.sunnytable.entity.Cdata;
import com.evigel.sunnytable.entity.Foid;
import com.evigel.sunnytable.entity.Ucid;
import com.evigel.sunnytable.entity.Udid;
import com.evigel.sunnytable.entity.Ufid;
import com.evigel.sunnytable.entity.Utid;
import com.evigel.sunnytable.exception.MyException;
import com.evigel.sunnytable.exception.ResultEnum;
import com.evigel.sunnytable.mapper.CdataMapper;
import com.evigel.sunnytable.mapper.FoidMapper;
import com.evigel.sunnytable.mapper.UcidMapper;
import com.evigel.sunnytable.mapper.UdidMapper;
import com.evigel.sunnytable.mapper.UfidMapper;
import com.evigel.sunnytable.mapper.UtidMapper;
import com.evigel.sunnytable.service.IDataSetService;
import com.evigel.sunnytable.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DataSetServiceImpl implements IDataSetService {

    @Autowired
    private UtidMapper utidMapper;

    @Autowired
    private UcidMapper ucidMapper;

    @Autowired
    private CdataMapper cdataMapper;

    @Autowired
    private UfidMapper ufidMapper;

    @Autowired
    private FoidMapper foidMapper;

    @Autowired
    private UdidMapper udidMapper;

    @Override
    public DataSetDto openDataset(long fid) {
        DataSetDto data;
        try {
            data = utidMapper.getDataSet(fid).get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new MyException(ResultEnum.DATA_ERROR, "文件id(fid)不存在");
        }

        QueryWrapper<Foid> foidQueryWrapper = new QueryWrapper<>();
        foidQueryWrapper.eq("fid", fid);
        Foid foid = foidMapper.selectOne(foidQueryWrapper);
        long tid = foid.getOid();

        QueryWrapper<Ucid> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tid", tid);
        List<Ucid> ucids = ucidMapper.selectList(queryWrapper);
        List<LineDataDto> columns = new ArrayList<>();
        for (Ucid o : ucids) {
            List<String> column = ucidMapper.getColumn(o.getCid());
            LineDataDto lineDataDto = new LineDataDto(o.getCn(), o.getCid(), column);
            columns.add(lineDataDto);
        }
        data.setDataArray(columns);
        return data;
    }

    @Override
    public void exportDataset(String uid, int type, DataSetDto data) {
        Tool.exportXlsFile(uid, type, data, "UTF-8");
    }

    @Override
    public long saveDataset(DataSetDto data) {
        String uid = data.getUserId();
        String name = data.getName();
        Utid utid = new Utid();
        utid.setUid(uid);
        utid.setTn(name);
        utidMapper.insert(utid);

        long tid = utid.getTid();
        List<Cdata> cdatas = new ArrayList<>();
        int len = 0;
        if (data.getDataArray() != null && data.getDataArray().get(0) != null
                && data.getDataArray().get(0).getLineData() != null)
            len = data.getDataArray().get(0).getLineData().size();
        for (LineDataDto lineDataDto : data.getDataArray()) {
            int len1 = 0;
            if (lineDataDto.getLineData() != null)
                len1 = lineDataDto.getLineData().size();
            if (len1 != len) {
                throw new MyException(ResultEnum.DATA_ERROR, "列数据长度不一致");
            }
            Ucid ucid = new Ucid();
            ucid.setUid(uid);
            ucid.setTid(tid);
            ucid.setCn(lineDataDto.getName());
            ucid.setType(0);
            ucidMapper.insert(ucid);

            long cid = ucid.getCid();
            int i = 0;
            for (String val : lineDataDto.getLineData()) {
                Cdata cdata = new Cdata(cid, ++i, val);
                cdatas.add(cdata);
            }
        }
        cdataMapper.insertBatchSomeColumn(cdatas);

        QueryWrapper<Udid> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid).eq("type", 3);
        Udid udid = udidMapper.selectOne(queryWrapper);

        Ufid ufid = new Ufid();
        ufid.setUid(uid);
        ufid.setDid(udid.getDid());
        ufid.setFname(name);
        ufidMapper.insert(ufid);

        long fid = ufid.getFid();
        Foid foid = new Foid();
        foid.setFid(fid);
        foid.setType(2);
        //foid.setCreateTime(LocalDateTime.now());
        //foid.setModifyTime(LocalDateTime.now());
        foid.setOid(tid);
        foidMapper.insert(foid);
        return tid;
    }

    @Override
    public long replaceDataset(DataSetDto data) {
        String uid = data.getUserId();
        QueryWrapper<Foid> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fid", data.getId());
        Foid foid = foidMapper.selectOne(queryWrapper);

        if (foid == null) {
            throw new MyException(ResultEnum.DATA_ERROR, "文件id(fid)不存在");
        }

        long tid = foid.getOid();
        //foid.setModifyTime(LocalDateTime.now());
        foidMapper.update(foid, new QueryWrapper<Foid>().eq("fid", foid.getFid()));

//        String uid = data.getUserId();
//        String name = data.getName();
//        Utid utid = new Utid();
//        utid.setUid(uid);
//        utid.setTn(name);
//        utidMapper.insert(utid);

//        long tid = utid.getTid();
        List<Cdata> cdatas = new ArrayList<>();
        List<Ucid> ucids = new ArrayList<>();
        int len = data.getDataArray().get(0).getLineData().size();
        for (LineDataDto lineDataDto : data.getDataArray()) {
            int len1 = lineDataDto.getLineData().size();
            if (len1 != len) {
                throw new MyException(ResultEnum.DATA_ERROR, "列数据长度不一致");
            }
            long cid = lineDataDto.getCid();
            Ucid ucid = new Ucid(uid, tid, lineDataDto.getName(), cid, 0);
            ucids.add(ucid);
            int i = 0;
            for (String val : lineDataDto.getLineData()) {
                Cdata cdata = new Cdata(cid, ++i, val);
                cdatas.add(cdata);
            }
        }
        cdataMapper.updateBatch(cdatas);
        ucidMapper.updateBatch(ucids);
        return tid;
    }
}

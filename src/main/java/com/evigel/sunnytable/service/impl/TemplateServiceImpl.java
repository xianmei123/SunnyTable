package com.evigel.sunnytable.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.evigel.sunnytable.dto.BarChartTemplateDto;
import com.evigel.sunnytable.dto.FanChartTemplateDto;
import com.evigel.sunnytable.dto.LineChartTemplateDto;
import com.evigel.sunnytable.dto.ScatterPlotTemplateDto;
import com.evigel.sunnytable.dto.TemplateInfoDto;
import com.evigel.sunnytable.entity.*;
import com.evigel.sunnytable.exception.MyException;
import com.evigel.sunnytable.exception.ResultEnum;
import com.evigel.sunnytable.mapper.*;
import com.evigel.sunnytable.service.ITemplateService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Watchable;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class TemplateServiceImpl implements ITemplateService {

    @Autowired
    private UdidMapper udidMapper;

    @Autowired
    private UmidMapper umidMapper;

    @Autowired
    private BarAxisMapper barAxisMapper;

    @Autowired
    private LineAxisMapper lineAxisMapper;

    @Autowired
    private PieAxisMapper pieAxisMapper;

    @Autowired
    private ScatterAxisMapper scatterAxisMapper;

    @Autowired
    private MpointMapper mpointMapper;

    @Autowired
    private MlineMapper mlineMapper;

    @Autowired
    private McolorMapper mcolorMapper;

    @Autowired
    private FoidMapper foidMapper;

    @Autowired
    private UfidMapper ufidMapper;

    @Autowired
    private UlogMapper ulogMapper;

    @Override
    public Long shareTemplate(String uid, Long fid) {
        TemplateInfoDto template = umidMapper.getTemplateByFid(fid);
        Long fid2 = 1L;
        if (template == null) {
            throw new MyException(ResultEnum.DATA_ERROR, "模板id(fid)不存在");
        }
        if (template.getType() == 1) {
            BarChartTemplateDto templateDto = openBarChartTemplate(fid);
            templateDto.setUserId(uid);
            fid2 = saveBarChartTemplate(templateDto);
        } else if (template.getType() == 2) {
            LineChartTemplateDto templateDto = openLineChartTemplate(fid);
            templateDto.setUserId(uid);
            fid2 = saveLineChartTemplate(templateDto);
        } else if (template.getType() == 3) {
            FanChartTemplateDto templateDto = openFanChartTemplate(fid);
            templateDto.setUserId(uid);
            fid2 = saveFanChartTemplate(templateDto);
        } else if (template.getType() == 4) {
            ScatterPlotTemplateDto templateDto = openScatterChartTemplate(fid);
            templateDto.setUserId(uid);
            fid2 = saveScatterChartTemplate(templateDto);
        }
        return fid2;
    }

    @Override
    public void updateScatter(ScatterPlotTemplateDto template) {
        List<String> uids = ulogMapper.getUsers();
        for (String uid : uids) {
            QueryWrapper<Umid> umidQueryWrapper = new QueryWrapper<>();
            umidQueryWrapper.eq("uid", uid).eq("mn", template.getName());
            Umid umid = umidMapper.selectList(umidQueryWrapper).get(0);
            long mid = umid.getMid();
            QueryWrapper<Foid> foidQueryWrapper = new QueryWrapper<>();
            foidQueryWrapper.eq("oid", mid);
            Foid foid = foidMapper.selectOne(foidQueryWrapper);
            template.setId(foid.getFid());
            replaceScatterChartTemplate(template);
        }

    }

    @Override
    public void updateFan(FanChartTemplateDto template) {
        List<String> uids = ulogMapper.getUsers();
        for (String uid : uids) {
            QueryWrapper<Umid> umidQueryWrapper = new QueryWrapper<>();
            umidQueryWrapper.eq("uid", uid).eq("mn", template.getName());
            Umid umid = umidMapper.selectList(umidQueryWrapper).get(0);
            long mid = umid.getMid();
            QueryWrapper<Foid> foidQueryWrapper = new QueryWrapper<>();
            foidQueryWrapper.eq("oid", mid);
            Foid foid = foidMapper.selectOne(foidQueryWrapper);
            template.setId(foid.getFid());
            replaceFanChartTemplate(template);
        }

    }

    @Override
    public void updateLine(LineChartTemplateDto template) {
        List<String> uids = ulogMapper.getUsers();
        for (String uid : uids) {
            QueryWrapper<Umid> umidQueryWrapper = new QueryWrapper<>();
            umidQueryWrapper.eq("uid", uid).eq("mn", template.getName());
            Umid umid = umidMapper.selectList(umidQueryWrapper).get(0);
            long mid = umid.getMid();
            QueryWrapper<Foid> foidQueryWrapper = new QueryWrapper<>();
            foidQueryWrapper.eq("oid", mid);
            Foid foid = foidMapper.selectOne(foidQueryWrapper);
            template.setId(foid.getFid());
            replaceLineChartTemplate(template);
        }
    }

    @Override
    public void updateBar(BarChartTemplateDto template) {
        List<String> uids = ulogMapper.getUsers();
        for (String uid : uids) {
            QueryWrapper<Umid> umidQueryWrapper = new QueryWrapper<>();
            umidQueryWrapper.eq("uid", uid).eq("mn", template.getName());
            Umid umid = umidMapper.selectList(umidQueryWrapper).get(0);
            long mid = umid.getMid();
            QueryWrapper<Foid> foidQueryWrapper = new QueryWrapper<>();
            foidQueryWrapper.eq("oid", mid);
            Foid foid = foidMapper.selectOne(foidQueryWrapper);
            template.setId(foid.getFid());
            replaceBarChartTemplate(template);
        }

    }

    @Override
    public List<TemplateInfoDto> displayTemplate(String uid, int maxNum) {
        List<TemplateInfoDto> templates = umidMapper.getTemplates(uid);
        List<TemplateInfoDto> templates2 = new ArrayList<>();
        int barNum = 0, lineNum = 0, scatterNum = 0, fanNum = 0;
        int typeMax = maxNum / 4;
        for (TemplateInfoDto template : templates) {
            if (template.getType() == 1 && barNum < typeMax) {
                templates2.add(template);
                barNum++;
            } else if (template.getType() == 2 && lineNum < typeMax) {
                templates2.add(template);
                lineNum++;
            } else if (template.getType() == 3 && fanNum < typeMax) {
                templates2.add(template);
                fanNum++;
            } else if (template.getType() == 4 && scatterNum < typeMax) {
                templates2.add(template);
                scatterNum++;
            }
        }
        return templates2;
    }

    @Override
    public BarChartTemplateDto openBarChartTemplate(long fid) {
        List<BarChartTemplateDto> templates = barAxisMapper.getTemplate(fid);
        if (templates.size() == 0) {
            throw new MyException(ResultEnum.DATA_ERROR, "文件id(fid)不存在");
        }
        BarChartTemplateDto barChartTemplateDto = templates.get(0);

        QueryWrapper<Foid> foidQueryWrapper = new QueryWrapper<>();
        foidQueryWrapper.eq("fid", fid);
        Foid foid = foidMapper.selectOne(foidQueryWrapper);
        long mid = foid.getOid();

        barChartTemplateDto.setColor(mcolorMapper.getColors(mid));
        return barChartTemplateDto;
    }

    public Boolean duplicateName(String uid,String name) {
        if (umidMapper.getTemplateByName(uid, name).size() != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public long saveBarChartTemplate(BarChartTemplateDto template) {
        Umid umid = new Umid();
        String uid = template.getUserId();
        String name = template.getName();
        if (duplicateName(uid, name)) {
            throw new MyException(ResultEnum.DATA_ERROR, "命名重复");
        }
        umid.setUid(uid);
        umid.setMn(name);
        umid.setType(1);
        umid.setIsVisible(template.getIsVisible());
        //System.out.println(template.getShowDigit());
        umidMapper.insert(umid);

        long mid = umid.getMid();
        template.setId(mid);
        BarAxis barAxis = new BarAxis(template);
        barAxisMapper.insert(barAxis);

        List<String> colors = template.getColor();
        List<Mcolor> mcolors = new ArrayList<>();

        for (int i = 0; i < colors.size(); i++) {
            Mcolor mcolor = new Mcolor(mid, i + 1, colors.get(i));
            mcolors.add(mcolor);
        }
        if (mcolors.size() != 0) {
            mcolorMapper.insertBatchSomeColumn(mcolors);
        }


        QueryWrapper<Udid> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid).eq("type", 2);
        Udid udid = udidMapper.selectOne(queryWrapper);

        Ufid ufid = new Ufid();
        ufid.setUid(uid);
        ufid.setDid(udid.getDid());
        ufid.setFname(name);
        ufid.setIsVisible(template.getIsVisible());
        ufidMapper.insert(ufid);

        long fid = ufid.getFid();
        Foid foid = new Foid();
        foid.setFid(fid);
        foid.setType(3);
        //foid.setCreateTime(LocalDateTime.now());
        //foid.setModifyTime(LocalDateTime.now());
        foid.setOid(mid);
        foidMapper.insert(foid);

        return fid;
    }

//    @Override
//    public List<TemplateInfoDto> displayLineTemplate(String uid, int maxNum) {
//        return null;
//    }

    @Override
    public LineChartTemplateDto openLineChartTemplate(long fid) {
        List<LineChartTemplateDto> templates = lineAxisMapper.getTemplate(fid);
        if (templates.size() == 0) {
            throw new MyException(ResultEnum.DATA_ERROR, "文件id(fid)不存在");
        }
        LineChartTemplateDto lineChartTemplateDto = templates.get(0);

        QueryWrapper<Foid> foidQueryWrapper = new QueryWrapper<>();
        foidQueryWrapper.eq("fid", fid);
        Foid foid = foidMapper.selectOne(foidQueryWrapper);
        long mid = foid.getOid();

        lineChartTemplateDto.setColor(mcolorMapper.getColors(mid));
        lineChartTemplateDto.setPoint(mpointMapper.getPoints(mid));
        lineChartTemplateDto.setLine(mlineMapper.getLines(mid));
        return lineChartTemplateDto;
    }

    @Override
    public long saveLineChartTemplate(LineChartTemplateDto template) {
        Umid umid = new Umid();
        String uid = template.getUserId();
        String name = template.getName();
        if (duplicateName(uid, name)) {
            throw new MyException(ResultEnum.DATA_ERROR, "命名重复");
        }
        umid.setUid(uid);
        umid.setMn(name);
        umid.setType(2);
        umid.setIsVisible(template.getIsVisible());
        umidMapper.insert(umid);

        long mid = umid.getMid();
        template.setId(mid);
        LineAxis lineAxis = new LineAxis(template);
        lineAxisMapper.insert(lineAxis);

        List<String> colors = template.getColor();
        List<Integer> lines = template.getLine();
        List<Integer> points = template.getPoint();
        List<Mcolor> mcolors = new ArrayList<>();
        List<Mline> mlines = new ArrayList<>();
        List<Mpoint> mpoints = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {

            Mline mline = new Mline(mid, i + 1, lines.get(i));


            mlines.add(mline);

        }
        for (int i = 0; i < colors.size(); i++) {
            Mcolor mcolor = new Mcolor(mid, i + 1, colors.get(i));

            mcolors.add(mcolor);

        }
        for (int i = 0; i < points.size(); i++) {
            Mpoint mpoint = new Mpoint(mid, i + 1, points.get(i));
            mpoints.add(mpoint);
        }

        if (mcolors.size() != 0) {
            mcolorMapper.insertBatchSomeColumn(mcolors);
        }
        if (mpoints.size() != 0) {
            mpointMapper.insertBatchSomeColumn(mpoints);
        }
        if (mlines.size() != 0) {
            mlineMapper.insertBatchSomeColumn(mlines);
        }

        QueryWrapper<Udid> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid).eq("type", 2);
        Udid udid = udidMapper.selectOne(queryWrapper);

        Ufid ufid = new Ufid();
        ufid.setUid(uid);
        ufid.setDid(udid.getDid());
        ufid.setFname(name);
        ufid.setIsVisible(template.getIsVisible());
        ufidMapper.insert(ufid);

        long fid = ufid.getFid();
        Foid foid = new Foid();
        //System.out.println(fid);
        foid.setFid(fid);
        foid.setType(3);
        //foid.setCreateTime(LocalDateTime.now());
        //foid.setModifyTime(LocalDateTime.now());
        foid.setOid(mid);
        foidMapper.insert(foid);

        return fid;
//        System.out.println("sssssssssssss");
//        long mid = 2;
//        List<String> colors = new ArrayList<>();
//        colors.add("a");
//        colors.add("b");
//        List<Mcolor> mcolors = new ArrayList<>();
//        for (int i = 0; i < colors.size(); i++) {
//            Mcolor mcolor = new Mcolor(mid, i+1, colors.get(i));
//            mcolors.add(mcolor);
//        }
//        mcolorMapper.insertBatchSomeColumn(mcolors);
    }

//    @Override
//    public List<TemplateInfoDto> displayFanTemplate(String uid, int maxNum) {
//        return null;
//    }

    @Override
    public FanChartTemplateDto openFanChartTemplate(long fid) {
        List<FanChartTemplateDto> templates = pieAxisMapper.getTemplate(fid);
        if (templates.size() == 0) {
            throw new MyException(ResultEnum.DATA_ERROR, "文件id(fid)不存在");
        }
        FanChartTemplateDto fanChartTemplateDto = templates.get(0);

        QueryWrapper<Foid> foidQueryWrapper = new QueryWrapper<>();
        foidQueryWrapper.eq("fid", fid);
        Foid foid = foidMapper.selectOne(foidQueryWrapper);
        long mid = foid.getOid();

        fanChartTemplateDto.setColor(mcolorMapper.getColors(mid));
        return fanChartTemplateDto;
    }

    @Override
    public long saveFanChartTemplate(FanChartTemplateDto template) {
        Umid umid = new Umid();
        String uid = template.getUserId();
        String name = template.getName();
        if (duplicateName(uid, name)) {
            throw new MyException(ResultEnum.DATA_ERROR, "命名重复");
        }
        umid.setUid(uid);
        umid.setMn(name);
        umid.setType(3);
        umid.setIsVisible(template.getIsVisible());
        umidMapper.insert(umid);

        long mid = umid.getMid();
        template.setId(mid);
        PieAxis pieAxis = new PieAxis(template);
        pieAxisMapper.insert(pieAxis);

        List<String> colors = template.getColor();
        List<Mcolor> mcolors = new ArrayList<>();

        for (int i = 0; i < colors.size(); i++) {
            Mcolor mcolor = new Mcolor(mid, i + 1, colors.get(i));
            mcolors.add(mcolor);
        }
        if (mcolors.size() != 0) {
            mcolorMapper.insertBatchSomeColumn(mcolors);
        }


        QueryWrapper<Udid> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid).eq("type", 2);
        Udid udid = udidMapper.selectOne(queryWrapper);


        Ufid ufid = new Ufid();
        ufid.setUid(uid);
        ufid.setDid(udid.getDid());
        ufid.setFname(name);
        ufid.setIsVisible(template.getIsVisible());
        ufidMapper.insert(ufid);

        long fid = ufid.getFid();
        Foid foid = new Foid();
        foid.setFid(fid);
        foid.setType(3);
        //foid.setCreateTime(LocalDateTime.now());
        //foid.setModifyTime(LocalDateTime.now());
        foid.setOid(mid);
        foidMapper.insert(foid);
        return fid;
    }

//    @Override
//    public List<TemplateInfoDto> displayScatterTemplate(String uid, int maxNum) {
//        return null;
//    }

    @Override
    public ScatterPlotTemplateDto openScatterChartTemplate(long fid) {
        List<ScatterPlotTemplateDto> templates = scatterAxisMapper.getTemplate(fid);
        if (templates.size() == 0) {
            throw new MyException(ResultEnum.DATA_ERROR, "文件id(fid)不存在");
        }
        ScatterPlotTemplateDto scatterPlotTemplateDto = templates.get(0);

        QueryWrapper<Foid> foidQueryWrapper = new QueryWrapper<>();
        foidQueryWrapper.eq("fid", fid);
        Foid foid = foidMapper.selectOne(foidQueryWrapper);
        long mid = foid.getOid();

        scatterPlotTemplateDto.setColor(mcolorMapper.getColors(mid));
        scatterPlotTemplateDto.setPoint(mpointMapper.getPoints(mid));
        return scatterPlotTemplateDto;
    }

    @Override
    public long saveScatterChartTemplate(ScatterPlotTemplateDto template) {
        Umid umid = new Umid();
        String uid = template.getUserId();
        String name = template.getName();
        if (duplicateName(uid, name)) {
            throw new MyException(ResultEnum.DATA_ERROR, "命名重复");
        }
        umid.setUid(uid);
        umid.setMn(name);
        umid.setType(4);
        umid.setIsVisible(template.getIsVisible());
        umidMapper.insert(umid);

        long mid = umid.getMid();
        template.setId(mid);
        ScatterAxis scatterAxis = new ScatterAxis(template);
        scatterAxisMapper.insert(scatterAxis);

        List<String> colors = template.getColor();
        List<Integer> points = template.getPoint();
        List<Mcolor> mcolors = new ArrayList<>();
        List<Mpoint> mpoints = new ArrayList<>();

        for (int i = 0; i < colors.size(); i++) {
            Mcolor mcolor = new Mcolor(mid, i + 1, colors.get(i));
            mcolors.add(mcolor);

        }
        for (int i = 0; i < points.size(); i++) {
            Mpoint mpoint = new Mpoint(mid, i + 1, points.get(i));
            mpoints.add(mpoint);
        }

        if (mcolors.size() != 0) {
            mcolorMapper.insertBatchSomeColumn(mcolors);
        }
        if (mpoints.size() != 0) {
            mpointMapper.insertBatchSomeColumn(mpoints);
        }


        QueryWrapper<Udid> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid).eq("type", 2);
        Udid udid = udidMapper.selectOne(queryWrapper);

        Ufid ufid = new Ufid();
        ufid.setUid(uid);
        ufid.setDid(udid.getDid());
        ufid.setFname(name);
        ufid.setIsVisible(template.getIsVisible());
        ufidMapper.insert(ufid);

        long fid = ufid.getFid();
        Foid foid = new Foid();
        foid.setFid(fid);
        foid.setType(3);
        //foid.setCreateTime(LocalDateTime.now());
        //foid.setModifyTime(LocalDateTime.now());
        foid.setOid(mid);
        foidMapper.insert(foid);

        return fid;
    }

    @Override
    public void replaceScatterChartTemplate(ScatterPlotTemplateDto template) {
        QueryWrapper<Foid> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fid", template.getId());
        Foid foid = foidMapper.selectOne(queryWrapper);

        if (foid == null) {
            throw new MyException(ResultEnum.DATA_ERROR, "文件id(fid)不存在");
        }

        long mid = foid.getOid();
        //foid.setModifyTime(LocalDateTime.now());
        Foid foid1 = new Foid();
        foidMapper.update(foid1, new QueryWrapper<Foid>().eq("fid", foid.getFid()));
//        if (template.getUserId().equals("evigel")) {
//            throw new MyException(ResultEnum.DATA_ERROR, "uid 错误");
//        }

        template.setId(mid);
        ScatterAxis scatterAxis = new ScatterAxis(template);
        scatterAxisMapper.update(scatterAxis, new QueryWrapper<ScatterAxis>().eq("mid", scatterAxis.getMid()));

        List<String> colors = template.getColor();
        List<Integer> points = template.getPoint();
        List<Mcolor> mcolors = new ArrayList<>();
        List<Mpoint> mpoints = new ArrayList<>();

        for (int i = 0; i < colors.size(); i++) {
            Mcolor mcolor = new Mcolor(mid, i + 1, colors.get(i));

            mcolors.add(mcolor);

        }
        for (int i = 0; i < points.size(); i++) {
            Mpoint mpoint = new Mpoint(mid, i + 1, points.get(i));
            mpoints.add(mpoint);
        }
        if (mcolors.size() != 0) {
            mcolorMapper.updateBatch(mcolors);
        }
        if (mpoints.size() != 0) {
            mpointMapper.updateBatch(mpoints);
        }


//        Foid foid = new Foid();
//        foid.setModifyTime(LocalDateTime.now());
//        foid.setOid(mid);
//        foidMapper.updateById(foid);

//        System.out.println("esssssssssssss");
//        long mid = 2;
//        List<String> colors = new ArrayList<>();
//        colors.add("e");
//        colors.add("f");
//        List<Mcolor> mcolors = new ArrayList<>();
//        for (int i = 0; i < colors.size(); i++) {
//            Mcolor mcolor = new Mcolor(mid, i+1, colors.get(i));
//            mcolors.add(mcolor);
//        }
//        mcolorMapper.updateBatch(mcolors);
    }

    @Override
    public void replaceLineChartTemplate(LineChartTemplateDto template) {
        QueryWrapper<Foid> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fid", template.getId());
        Foid foid = foidMapper.selectOne(queryWrapper);

        if (foid == null) {
            throw new MyException(ResultEnum.DATA_ERROR, "文件id(fid)不存在");
        }

        long mid = foid.getOid();
        //foid.setModifyTime(LocalDateTime.now());
        Foid foid1 = new Foid();
        foidMapper.update(foid1, new QueryWrapper<Foid>().eq("fid", foid.getFid()));

        template.setId(mid);
        LineAxis lineAxis = new LineAxis(template);
        lineAxisMapper.update(lineAxis, new QueryWrapper<LineAxis>().eq("mid", lineAxis.getMid()));

        List<String> colors = template.getColor();
        List<Integer> points = template.getPoint();
        List<Integer> lines = template.getLine();
        List<Mcolor> mcolors = new ArrayList<>();
        List<Mpoint> mpoints = new ArrayList<>();
        List<Mline> mlines = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {

            Mline mline = new Mline(mid, i + 1, lines.get(i));
            mlines.add(mline);

        }
        for (int i = 0; i < colors.size(); i++) {
            Mcolor mcolor = new Mcolor(mid, i + 1, colors.get(i));

            mcolors.add(mcolor);

        }
        for (int i = 0; i < points.size(); i++) {
            Mpoint mpoint = new Mpoint(mid, i + 1, points.get(i));
            mpoints.add(mpoint);
        }

        if (mcolors.size() != 0) {
            mcolorMapper.updateBatch(mcolors);
        }
        if (mpoints.size() != 0) {
            mpointMapper.updateBatch(mpoints);
        }
        if (mlines.size() != 0) {
            mlineMapper.updateBatch(mlines);
        }


    }

    @Override
    public void replaceFanChartTemplate(FanChartTemplateDto template) {
        QueryWrapper<Foid> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fid", template.getId());
        Foid foid = foidMapper.selectOne(queryWrapper);

        if (foid == null) {
            throw new MyException(ResultEnum.DATA_ERROR, "文件id(fid)不存在");
        }

        long mid = foid.getOid();
        //foid.setModifyTime(LocalDateTime.now());
        Foid foid1 = new Foid();
        foidMapper.update(foid1, new QueryWrapper<Foid>().eq("fid", foid.getFid()));

        template.setId(mid);
        PieAxis pieAxis = new PieAxis(template);
        pieAxisMapper.update(pieAxis, new QueryWrapper<PieAxis>().eq("mid", pieAxis.getMid()));

        List<String> colors = template.getColor();
        List<Mcolor> mcolors = new ArrayList<>();

        for (int i = 0; i < colors.size(); i++) {
            Mcolor mcolor = new Mcolor(mid, i + 1, colors.get(i));
            mcolors.add(mcolor);
        }
        if (mcolors.size() != 0) {
            mcolorMapper.updateBatch(mcolors);
        }


    }

    @Override
    public void replaceBarChartTemplate(BarChartTemplateDto template) {
        QueryWrapper<Foid> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fid", template.getId());
        Foid foid = foidMapper.selectOne(queryWrapper);

        if (foid == null) {
            throw new MyException(ResultEnum.DATA_ERROR, "文件id(fid)不存在");
        }

        long mid = foid.getOid();

//        foid.setCreateTime(null);
//        foid.setModifyTime(null);

        Foid foid1 = new Foid();
        foidMapper.update(foid1, new QueryWrapper<Foid>().eq("fid", foid.getFid()));


        template.setId(mid);
        BarAxis barAxis = new BarAxis(template);
        barAxisMapper.update(barAxis, new QueryWrapper<BarAxis>().eq("mid", barAxis.getMid()));

        List<String> colors = template.getColor();
        List<Mcolor> mcolors = new ArrayList<>();

        for (int i = 0; i < colors.size(); i++) {
            Mcolor mcolor = new Mcolor(mid, i + 1, colors.get(i));
            mcolors.add(mcolor);
        }
        if (mcolors.size() != 0) {
            mcolorMapper.updateBatch(mcolors);
        }

    }
}

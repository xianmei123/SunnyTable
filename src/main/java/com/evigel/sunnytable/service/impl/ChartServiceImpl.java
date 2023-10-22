package com.evigel.sunnytable.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.evigel.sunnytable.dto.BarChartDto;
import com.evigel.sunnytable.dto.BarChartTemplateDto;
import com.evigel.sunnytable.dto.DataSetDto;
import com.evigel.sunnytable.dto.FanChartDto;
import com.evigel.sunnytable.dto.FanChartTemplateDto;
import com.evigel.sunnytable.dto.LineChartDto;
import com.evigel.sunnytable.dto.LineChartTemplateDto;
import com.evigel.sunnytable.dto.LineDataDto;
import com.evigel.sunnytable.dto.ScatterPlotDto;
import com.evigel.sunnytable.dto.ScatterPlotTemplateDto;
import com.evigel.sunnytable.entity.Foid;
import com.evigel.sunnytable.entity.Pdata;
import com.evigel.sunnytable.entity.Py;
import com.evigel.sunnytable.entity.Udid;
import com.evigel.sunnytable.entity.Ufid;
import com.evigel.sunnytable.entity.Upid;
import com.evigel.sunnytable.exception.MyException;
import com.evigel.sunnytable.exception.ResultEnum;
import com.evigel.sunnytable.mapper.FoidMapper;
import com.evigel.sunnytable.mapper.PdataMapper;
import com.evigel.sunnytable.mapper.PyMapper;
import com.evigel.sunnytable.mapper.UdidMapper;
import com.evigel.sunnytable.mapper.UfidMapper;
import com.evigel.sunnytable.mapper.UpidMapper;
import com.evigel.sunnytable.service.IChartService;
import com.evigel.sunnytable.service.IDataSetService;
import com.evigel.sunnytable.service.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service
public class ChartServiceImpl implements IChartService {

    @Autowired
    private UpidMapper upidMapper;

    @Autowired
    private UfidMapper ufidMapper;

    @Autowired
    private UdidMapper udidMapper;

    @Autowired
    private PdataMapper pdataMapper;

    @Autowired
    private PyMapper pyMapper;

    @Autowired
    private FoidMapper foidMapper;

    @Autowired
    private ITemplateService templateService;

    @Autowired
    private IDataSetService dataSetService;

    public Boolean duplicateName(String uid,String name) {
        if (upidMapper.getChartByName(uid, name).size() != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public BarChartDto openBarChart(long fid) {
        QueryWrapper<Foid> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("fid", fid);
        List<Foid> foids = foidMapper.selectList(wrapper1);
        if (foids.size() == 0) {
            throw new MyException(ResultEnum.DATA_ERROR, "文件id(fid)不存在");
        }
        long pid = foids.get(0).getOid();

        QueryWrapper<Upid> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("pid", pid);

        List<Upid> upids = upidMapper.selectList(wrapper2);
        Upid upid = upids.get(0);
        long mid = upid.getMid();
        long tid = upid.getTid();

        QueryWrapper<Pdata> wrapper3 = new QueryWrapper<>();
        wrapper3.eq("pid", pid);
        List<Pdata> pdatas = pdataMapper.selectList(wrapper3);
        Pdata pdata = pdatas.get(0);
        QueryWrapper<Foid> wrapper = new QueryWrapper<>();
        wrapper.eq("oid", tid);
        List<Foid> tfoids = foidMapper.selectList(wrapper);
        long tfid = tfoids.get(0).getFid();
        QueryWrapper<Foid> wrapper4 = new QueryWrapper<>();
        wrapper4.eq("oid", mid);
        List<Foid> mfoids = foidMapper.selectList(wrapper4);
        long mfid = mfoids.get(0).getFid();
        BarChartTemplateDto template = templateService.openBarChartTemplate(mfid);
        DataSetDto dataSet = dataSetService.openDataset(tfid);
        long id = foids.get(0).getFid();
        String name = upid.getPName();
        String xLabel = pdata.getXlabel();
        String yLabel = pdata.getYlabel();
        Long xBegin = pdata.getXbegin();
        Long yBegin = pdata.getYbegin();
        Long xId = pdata.getCid();
        Double length = pdata.getWheight();
        Double width = pdata.getWwidth();

        QueryWrapper<Py> wrapper5 = new QueryWrapper<>();
        wrapper5.eq("pid", pid);
        List<Py> pys = pyMapper.selectList(wrapper5);
        List<Long> yId = new ArrayList<>();
        for (int i = 0; i < pys.size(); i++) {
            yId.add(pys.get(i).getCid());
            // TODO 顺序
        }

        BarChartDto barChart = new BarChartDto(id, name, xLabel, yLabel,
                xId, yId, xBegin, yBegin, length, width, template, dataSet);
        return barChart;
    }

    @Override
    public Long saveBarChart(BarChartDto chart) {
        Upid upid = new Upid();
        BarChartTemplateDto template = chart.getBarChart();
        DataSetDto dataSet = chart.getData();
        upid.setUid(template.getUserId());
        long fid = templateService.saveBarChartTemplate(template);
        QueryWrapper<Foid> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("fid", fid);
        List<Foid> foids = foidMapper.selectList(wrapper1);
        Foid foidm = foids.get(0);
        long mid = foidm.getOid();
        upid.setMid(mid);
        upid.setTid(dataSetService.saveDataset(dataSet));
        upid.setPName(chart.getName());
        String uid = template.getUserId();
        String name = chart.getName();
        if (duplicateName(uid, name)) {
            throw new MyException(ResultEnum.DATA_ERROR, "命名重复");
        }
        upidMapper.insert(upid);
        long pid = upid.getPid();

        Pdata pdata = new Pdata();
        pdata.setPid(pid);
        pdata.setXlabel(chart.getXlabel());
        pdata.setYlabel(chart.getYlabel());
        pdata.setXbegin(chart.getXbegin());
        pdata.setYbegin(chart.getYbegin());
        pdata.setCid(chart.getXid());
        pdata.setWheight(chart.getLength());
        pdata.setWwidth(chart.getWidth());

        pdataMapper.insert(pdata);

        List<Long> ldata = chart.getYid();
        List<Py> newldata = new ArrayList<>();
        for (int i = 0; i < ldata.size(); i++) {
            Py py = new Py();
            py.setPid(pid);
            py.setNo(i);
            py.setCid(ldata.get(i));
            newldata.add(py);
        }
        pyMapper.insertBatchSomeColumn(newldata);

        Ufid ufid = new Ufid();
        //ufid.setUid(template.getUserId());
        QueryWrapper<Udid> wrapper = new QueryWrapper<>();
        Map<String, Object> queryParamsMap = new HashMap<>();
        queryParamsMap.put("uid", template.getUserId());
        queryParamsMap.put("type", 1);

        wrapper.allEq(queryParamsMap);

        List<Udid> udids = udidMapper.selectList(wrapper);

        ufid.setUid(template.getUserId());
        ufid.setDid(udids.get(0).getDid());
        ufid.setFname(chart.getName());
        ufidMapper.insert(ufid);

        long id = ufid.getFid();

        Foid foid = new Foid();
        foid.setFid(id);
        foid.setType(1);
        //foid.setCreateTime(LocalDateTime.now());
        //foid.setModifyTime(LocalDateTime.now());
        foid.setOid(pid);
        foidMapper.insert(foid);
        return ufid.getFid();
    }

    @Override
    public void replaceBarChart(BarChartDto chart) {
        //Foid foid = foidMapper.selectById(chart.getId());
        QueryWrapper<Foid> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("fid", chart.getId());
        List<Foid> foids = foidMapper.selectList(wrapper1);
        if (foids.size() == 0) {
            throw new MyException(ResultEnum.DATA_ERROR, "文件id(fid)不存在");
        }
        Foid foid = foids.get(0);
        long pid = foid.getOid();

        BarChartTemplateDto template = chart.getBarChart();
        DataSetDto dataSet = chart.getData();
        templateService.replaceBarChartTemplate(template);
        dataSetService.replaceDataset(dataSet);

        Upid upidUpdate = new Upid();
        upidUpdate.setPName(chart.getName());
        UpdateWrapper<Upid> wrapper2 = new UpdateWrapper<>();
        wrapper2.eq("pid", pid);
        upidMapper.update(upidUpdate, wrapper2);

        Pdata pdata = new Pdata();
        pdata.setCid(chart.getXid());
        pdata.setXlabel(chart.getXlabel());
        pdata.setYlabel(chart.getYlabel());
        pdata.setXbegin(chart.getXbegin());
        pdata.setYbegin(chart.getYbegin());

        pdata.setWheight(chart.getLength());
        pdata.setWwidth(chart.getWidth());

        UpdateWrapper<Pdata> wrapper3 = new UpdateWrapper<>();
        wrapper3.eq("pid", pid);
        pdataMapper.update(pdata, wrapper3);
        //Py py = new Py();
        //py.setPid(pid);
        QueryWrapper<Py> wrapper6 = new QueryWrapper<>();
        wrapper6.eq("pid", pid);
        pyMapper.delete(wrapper6);
        List<Long> ldata = chart.getYid();
        List<Py> newldata = new ArrayList<>();
        for (int i = 0; i < ldata.size(); i++) {
            Py py = new Py();
            py.setPid(pid);
            py.setNo(i);
            py.setCid(ldata.get(i));
            newldata.add(py);
        }
        pyMapper.insertBatchSomeColumn(newldata);

        Ufid ufid = new Ufid();
        ufid.setFname(chart.getName());
        UpdateWrapper<Ufid> wrapper4 = new UpdateWrapper<>();
        wrapper4.eq("fid", chart.getId());
        ufidMapper.update(ufid, wrapper4);

        Foid foid2 = new Foid();
        //foid2.setModifyTime(LocalDateTime.now());
        UpdateWrapper<Foid> wrapper5 = new UpdateWrapper<>();
        wrapper5.eq("fid", chart.getId());
        foidMapper.update(foid2, wrapper5);
    }

    @Override
    public FanChartDto openFanChart(long fid) {
        QueryWrapper<Foid> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("fid", fid);
        List<Foid> foids = foidMapper.selectList(wrapper1);
        if (foids.size() == 0) {
            throw new MyException(ResultEnum.DATA_ERROR, "文件id(fid)不存在");
        }
        long pid = foids.get(0).getOid();

        QueryWrapper<Upid> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("pid", pid);

        List<Upid> upids = upidMapper.selectList(wrapper2);
        Upid upid = upids.get(0);
        long mid = upid.getMid();
        long tid = upid.getTid();

        QueryWrapper<Pdata> wrapper3 = new QueryWrapper<>();
        wrapper3.eq("pid", pid);
        List<Pdata> pdatas = pdataMapper.selectList(wrapper3);
        Pdata pdata = pdatas.get(0);
        QueryWrapper<Foid> wrapper = new QueryWrapper<>();
        wrapper.eq("oid", tid);
        List<Foid> tfoids = foidMapper.selectList(wrapper);
        long tfid = tfoids.get(0).getFid();
        QueryWrapper<Foid> wrapper4 = new QueryWrapper<>();
        wrapper4.eq("oid", mid);
        List<Foid> mfoids = foidMapper.selectList(wrapper4);
        long mfid = mfoids.get(0).getFid();
        FanChartTemplateDto template = templateService.openFanChartTemplate(mfid);
        DataSetDto dataSet = dataSetService.openDataset(tfid);
        long id = foids.get(0).getFid();
        String name = upid.getPName();
        String xLabel = pdata.getXlabel();
        String yLabel = pdata.getYlabel();
        Long xBegin = pdata.getXbegin();
        Long yBegin = pdata.getYbegin();
        Long xId = pdata.getCid();
        Double length = pdata.getWheight();
        Double width = pdata.getWwidth();

        QueryWrapper<Py> wrapper5 = new QueryWrapper<>();
        wrapper5.eq("pid", pid);
        List<Py> pys = pyMapper.selectList(wrapper5);
        List<Long> yId = new ArrayList<>();
        for (int i = 0; i < pys.size(); i++) {
            yId.add(pys.get(i).getCid());
            // TODO 顺序
        }

        FanChartDto fanChart = new FanChartDto(id, name, xLabel, yLabel,
                xId, yId, xBegin, yBegin, length, width, template, dataSet);
        return fanChart;

    }

    @Override
    public Long saveFanChart(FanChartDto chart) {
        Upid upid = new Upid();
        FanChartTemplateDto template = chart.getFanChart();
        DataSetDto dataSet = chart.getData();
        upid.setUid(template.getUserId());
        long fid = templateService.saveFanChartTemplate(template);
        QueryWrapper<Foid> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("fid", fid);
        List<Foid> foids = foidMapper.selectList(wrapper1);
        Foid foidm = foids.get(0);
        long mid = foidm.getOid();
        upid.setMid(mid);
        upid.setTid(dataSetService.saveDataset(dataSet));
        upid.setPName(chart.getName());
        String uid = template.getUserId();
        String name = chart.getName();
        if (duplicateName(uid, name)) {
            throw new MyException(ResultEnum.DATA_ERROR, "命名重复");
        }
        upidMapper.insert(upid);
        long pid = upid.getPid();

        Pdata pdata = new Pdata();
        pdata.setPid(pid);
        pdata.setXlabel(chart.getXlabel());
        pdata.setYlabel(chart.getYlabel());
        pdata.setXbegin(chart.getXbegin());
        pdata.setYbegin(chart.getYbegin());
        pdata.setCid(chart.getXid());
        pdata.setWheight(chart.getLength());
        pdata.setWwidth(chart.getWidth());

        pdataMapper.insert(pdata);

        List<Long> ldata = chart.getYid();
        List<Py> newldata = new ArrayList<>();
        for (int i = 0; i < ldata.size(); i++) {
            Py py = new Py();
            py.setPid(pid);
            py.setNo(i);
            py.setCid(ldata.get(i));
            newldata.add(py);
        }
        pyMapper.insertBatchSomeColumn(newldata);

        Ufid ufid = new Ufid();
        //ufid.setUid(template.getUserId());
        QueryWrapper<Udid> wrapper = new QueryWrapper<>();
        Map<String, Object> queryParamsMap = new HashMap<>();
        queryParamsMap.put("uid", template.getUserId());
        queryParamsMap.put("type", 1);

        wrapper.allEq(queryParamsMap);

        List<Udid> udids = udidMapper.selectList(wrapper);

        ufid.setUid(template.getUserId());
        ufid.setDid(udids.get(0).getDid());
        ufid.setFname(chart.getName());
        ufidMapper.insert(ufid);

        long id = ufid.getFid();

        Foid foid = new Foid();
        foid.setFid(id);
        foid.setType(1);
        //foid.setCreateTime(LocalDateTime.now());
        //foid.setModifyTime(LocalDateTime.now());
        foid.setOid(pid);
        foidMapper.insert(foid);
        return ufid.getFid();
    }

    @Override
    public void replaceFanChart(FanChartDto chart) {
        QueryWrapper<Foid> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("fid", chart.getId());
        List<Foid> foids = foidMapper.selectList(wrapper1);
        if (foids.size() == 0) {
            throw new MyException(ResultEnum.DATA_ERROR, "文件id(fid)不存在");
        }
        Foid foid = foids.get(0);
        long pid = foid.getOid();

        FanChartTemplateDto template = chart.getFanChart();
        DataSetDto dataSet = chart.getData();
        templateService.replaceFanChartTemplate(template);
        dataSetService.replaceDataset(dataSet);

        Upid upidUpdate = new Upid();
        upidUpdate.setPName(chart.getName());
        UpdateWrapper<Upid> wrapper2 = new UpdateWrapper<>();
        wrapper2.eq("pid", pid);
        upidMapper.update(upidUpdate, wrapper2);

        Pdata pdata = new Pdata();
        pdata.setCid(chart.getXid());
        pdata.setXlabel(chart.getXlabel());
        pdata.setYlabel(chart.getYlabel());
        pdata.setXbegin(chart.getXbegin());
        pdata.setYbegin(chart.getYbegin());

        pdata.setWheight(chart.getLength());
        pdata.setWwidth(chart.getWidth());

        UpdateWrapper<Pdata> wrapper3 = new UpdateWrapper<>();
        wrapper3.eq("pid", pid);
        pdataMapper.update(pdata, wrapper3);
        //Py py = new Py();
        //py.setPid(pid);
        QueryWrapper<Py> wrapper6 = new QueryWrapper<>();
        wrapper6.eq("pid", pid);
        pyMapper.delete(wrapper6);
        List<Long> ldata = chart.getYid();
        List<Py> newldata = new ArrayList<>();
        for (int i = 0; i < ldata.size(); i++) {
            Py py = new Py();
            py.setPid(pid);
            py.setNo(i);
            py.setCid(ldata.get(i));
            newldata.add(py);
        }
        pyMapper.insertBatchSomeColumn(newldata);

        Ufid ufid = new Ufid();
        ufid.setFname(chart.getName());
        UpdateWrapper<Ufid> wrapper4 = new UpdateWrapper<>();
        wrapper4.eq("fid", chart.getId());
        ufidMapper.update(ufid, wrapper4);

        Foid foid2 = new Foid();
        //foid2.setModifyTime(LocalDateTime.now());
        UpdateWrapper<Foid> wrapper5 = new UpdateWrapper<>();
        wrapper5.eq("fid", chart.getId());
        foidMapper.update(foid2, wrapper5);
    }

    @Override
    public LineChartDto openLineChart(long fid) {
        QueryWrapper<Foid> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("fid", fid);
        List<Foid> foids = foidMapper.selectList(wrapper1);
        if (foids.size() == 0) {
            throw new MyException(ResultEnum.DATA_ERROR, "文件id(fid)不存在");
        }
        long pid = foids.get(0).getOid();

        QueryWrapper<Upid> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("pid", pid);

        List<Upid> upids = upidMapper.selectList(wrapper2);
        Upid upid = upids.get(0);
        long mid = upid.getMid();
        long tid = upid.getTid();

        QueryWrapper<Pdata> wrapper3 = new QueryWrapper<>();
        wrapper3.eq("pid", pid);
        List<Pdata> pdatas = pdataMapper.selectList(wrapper3);
        Pdata pdata = pdatas.get(0);
        QueryWrapper<Foid> wrapper = new QueryWrapper<>();
        wrapper.eq("oid", tid);
        List<Foid> tfoids = foidMapper.selectList(wrapper);
        long tfid = tfoids.get(0).getFid();
        QueryWrapper<Foid> wrapper4 = new QueryWrapper<>();
        wrapper4.eq("oid", mid);
        List<Foid> mfoids = foidMapper.selectList(wrapper4);
        long mfid = mfoids.get(0).getFid();
        LineChartTemplateDto template = templateService.openLineChartTemplate(mfid);
        DataSetDto dataSet = dataSetService.openDataset(tfid);
        long id = foids.get(0).getFid();
        String name = upid.getPName();
        String xLabel = pdata.getXlabel();
        String yLabel = pdata.getYlabel();
        Long xBegin = pdata.getXbegin();
        Long yBegin = pdata.getYbegin();
        Long xId = pdata.getCid();
        Double length = pdata.getWheight();
        Double width = pdata.getWwidth();

        QueryWrapper<Py> wrapper5 = new QueryWrapper<>();
        wrapper5.eq("pid", pid);
        List<Py> pys = pyMapper.selectList(wrapper5);
        List<Long> yId = new ArrayList<>();
        for (int i = 0; i < pys.size(); i++) {
            yId.add(pys.get(i).getCid());
            // TODO 顺序
        }

        LineChartDto fanChart = new LineChartDto(id, name, xLabel, yLabel,
                xId, yId, xBegin, yBegin, length, width, template, dataSet);
        return fanChart;
    }

    @Override
    public Long saveLineChart(LineChartDto chart) {
        Upid upid = new Upid();
        LineChartTemplateDto template = chart.getLineChart();
        DataSetDto dataSet = chart.getData();
        upid.setUid(template.getUserId());
        long fid = templateService.saveLineChartTemplate(template);
        QueryWrapper<Foid> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("fid", fid);
        List<Foid> foids = foidMapper.selectList(wrapper1);
        Foid foidm = foids.get(0);
        long mid = foidm.getOid();
        upid.setMid(mid);
        upid.setTid(dataSetService.saveDataset(dataSet));
        upid.setPName(chart.getName());
        String uid = template.getUserId();
        String name = chart.getName();
        if (duplicateName(uid, name)) {
            throw new MyException(ResultEnum.DATA_ERROR, "命名重复");
        }
        upidMapper.insert(upid);
        long pid = upid.getPid();

        Pdata pdata = new Pdata();
        pdata.setPid(pid);
        pdata.setXlabel(chart.getXlabel());
        pdata.setYlabel(chart.getYlabel());
        pdata.setXbegin(chart.getXbegin());
        pdata.setYbegin(chart.getYbegin());
        pdata.setCid(chart.getXid());
        pdata.setWheight(chart.getLength());
        pdata.setWwidth(chart.getWidth());

        pdataMapper.insert(pdata);

        List<Long> ldata = chart.getYid();
        List<Py> newldata = new ArrayList<>();
        for (int i = 0; i < ldata.size(); i++) {
            Py py = new Py();
            py.setPid(pid);
            py.setNo(i);
            py.setCid(ldata.get(i));
            newldata.add(py);
        }
        pyMapper.insertBatchSomeColumn(newldata);

        Ufid ufid = new Ufid();
        //ufid.setUid(template.getUserId());
        QueryWrapper<Udid> wrapper = new QueryWrapper<>();
        Map<String, Object> queryParamsMap = new HashMap<>();
        queryParamsMap.put("uid", template.getUserId());
        queryParamsMap.put("type", 1);

        wrapper.allEq(queryParamsMap);

        List<Udid> udids = udidMapper.selectList(wrapper);

        ufid.setUid(template.getUserId());
        ufid.setDid(udids.get(0).getDid());
        ufid.setFname(chart.getName());
        ufidMapper.insert(ufid);

        long id = ufid.getFid();

        Foid foid = new Foid();
        foid.setFid(id);
        foid.setType(1);
        //foid.setCreateTime(LocalDateTime.now());
        //foid.setModifyTime(LocalDateTime.now());
        foid.setOid(pid);
        foidMapper.insert(foid);
        return ufid.getFid();

    }

    @Override
    public void replaceLineChart(LineChartDto chart) {
        QueryWrapper<Foid> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("fid", chart.getId());
        List<Foid> foids = foidMapper.selectList(wrapper1);
        if (foids.size() == 0) {
            throw new MyException(ResultEnum.DATA_ERROR, "文件id(fid)不存在");
        }
        Foid foid = foids.get(0);
        long pid = foid.getOid();

        LineChartTemplateDto template = chart.getLineChart();
        DataSetDto dataSet = chart.getData();
        templateService.replaceLineChartTemplate(template);
        dataSetService.replaceDataset(dataSet);

        Upid upidUpdate = new Upid();
        upidUpdate.setPName(chart.getName());
        UpdateWrapper<Upid> wrapper2 = new UpdateWrapper<>();
        wrapper2.eq("pid", pid);
        upidMapper.update(upidUpdate, wrapper2);

        Pdata pdata = new Pdata();
        pdata.setCid(chart.getXid());
        pdata.setXlabel(chart.getXlabel());
        pdata.setYlabel(chart.getYlabel());
        pdata.setXbegin(chart.getXbegin());
        pdata.setYbegin(chart.getYbegin());

        pdata.setWheight(chart.getLength());
        pdata.setWwidth(chart.getWidth());

        UpdateWrapper<Pdata> wrapper3 = new UpdateWrapper<>();
        wrapper3.eq("pid", pid);
        pdataMapper.update(pdata, wrapper3);
        //Py py = new Py();
        //py.setPid(pid);
        QueryWrapper<Py> wrapper6 = new QueryWrapper<>();
        wrapper6.eq("pid", pid);
        pyMapper.delete(wrapper6);
        List<Long> ldata = chart.getYid();
        List<Py> newldata = new ArrayList<>();
        for (int i = 0; i < ldata.size(); i++) {
            Py py = new Py();
            py.setPid(pid);
            py.setNo(i);
            py.setCid(ldata.get(i));
            newldata.add(py);
        }
        pyMapper.insertBatchSomeColumn(newldata);

        Ufid ufid = new Ufid();
        ufid.setFname(chart.getName());
        UpdateWrapper<Ufid> wrapper4 = new UpdateWrapper<>();
        wrapper4.eq("fid", chart.getId());
        ufidMapper.update(ufid, wrapper4);

        Foid foid2 = new Foid();
        //foid2.setModifyTime(LocalDateTime.now());
        UpdateWrapper<Foid> wrapper5 = new UpdateWrapper<>();
        wrapper5.eq("fid", chart.getId());
        foidMapper.update(foid2, wrapper5);
    }

    @Override
    public ScatterPlotDto openScatterChart(long fid) {
        QueryWrapper<Foid> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("fid", fid);
        List<Foid> foids = foidMapper.selectList(wrapper1);
        if (foids.size() == 0) {
            throw new MyException(ResultEnum.DATA_ERROR, "文件id(fid)不存在");
        }
        long pid = foids.get(0).getOid();

        QueryWrapper<Upid> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("pid", pid);

        List<Upid> upids = upidMapper.selectList(wrapper2);
        Upid upid = upids.get(0);
        long mid = upid.getMid();
        long tid = upid.getTid();

        QueryWrapper<Pdata> wrapper3 = new QueryWrapper<>();
        wrapper3.eq("pid", pid);
        List<Pdata> pdatas = pdataMapper.selectList(wrapper3);
        Pdata pdata = pdatas.get(0);
        QueryWrapper<Foid> wrapper = new QueryWrapper<>();
        wrapper.eq("oid", tid);
        List<Foid> tfoids = foidMapper.selectList(wrapper);
        long tfid = tfoids.get(0).getFid();
        QueryWrapper<Foid> wrapper4 = new QueryWrapper<>();
        wrapper4.eq("oid", mid);
        List<Foid> mfoids = foidMapper.selectList(wrapper4);
        long mfid = mfoids.get(0).getFid();
        ScatterPlotTemplateDto template = templateService.openScatterChartTemplate(mfid);
        DataSetDto dataSet = dataSetService.openDataset(tfid);
        long id = foids.get(0).getFid();
        String name = upid.getPName();
        String xLabel = pdata.getXlabel();
        String yLabel = pdata.getYlabel();
        Long xBegin = pdata.getXbegin();
        Long yBegin = pdata.getYbegin();
        Long xId = pdata.getCid();
        Double length = pdata.getWheight();
        Double width = pdata.getWwidth();

        QueryWrapper<Py> wrapper5 = new QueryWrapper<>();
        wrapper5.eq("pid", pid);
        List<Py> pys = pyMapper.selectList(wrapper5);
        List<Long> yId = new ArrayList<>();
        for (int i = 0; i < pys.size(); i++) {
            yId.add(pys.get(i).getCid());
            // TODO 顺序
        }

        ScatterPlotDto fanChart = new ScatterPlotDto(id, name, xLabel, yLabel,
                xId, yId, xBegin, yBegin, length, width, template, dataSet);
        return fanChart;
    }

    @Override
    public Long saveScatterChart(ScatterPlotDto chart) {
        Upid upid = new Upid();
        ScatterPlotTemplateDto template = chart.getScatterPlot();
        DataSetDto dataSet = chart.getData();
        upid.setUid(template.getUserId());
        long fid = templateService.saveScatterChartTemplate(template);
        QueryWrapper<Foid> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("fid",fid );
        List<Foid> foids = foidMapper.selectList(wrapper1);
        Foid foidm = foids.get(0);
        long mid = foidm.getOid();
        upid.setMid(mid);
        upid.setTid(dataSetService.saveDataset(dataSet));
        upid.setPName(chart.getName());
        String uid = template.getUserId();
        String name = chart.getName();
        if (duplicateName(uid, name)) {
            throw new MyException(ResultEnum.DATA_ERROR, "命名重复");
        }
        upidMapper.insert(upid);
        long pid = upid.getPid();

        Pdata pdata = new Pdata();
        pdata.setPid(pid);
        pdata.setXlabel(chart.getXlabel());
        pdata.setYlabel(chart.getYlabel());
        pdata.setXbegin(chart.getXbegin());
        pdata.setYbegin(chart.getYbegin());
        pdata.setCid(chart.getXid());
        pdata.setWheight(chart.getLength());
        pdata.setWwidth(chart.getWidth());

        pdataMapper.insert(pdata);

        List<Long> ldata = chart.getYid();
        List<Py> newldata = new ArrayList<>();
        for (int i = 0; i < ldata.size(); i++) {
            Py py = new Py();
            py.setPid(pid);
            py.setNo(i);
            py.setCid(ldata.get(i));
            newldata.add(py);
        }
        pyMapper.insertBatchSomeColumn(newldata);

        Ufid ufid = new Ufid();
        //ufid.setUid(template.getUserId());
        QueryWrapper<Udid> wrapper = new QueryWrapper<>();
        Map<String, Object> queryParamsMap = new HashMap<>();
        queryParamsMap.put("uid", template.getUserId());
        queryParamsMap.put("type", 1);

        wrapper.allEq(queryParamsMap);

        List<Udid> udids = udidMapper.selectList(wrapper);

        ufid.setUid(template.getUserId());
        ufid.setDid(udids.get(0).getDid());
        ufid.setFname(chart.getName());
        ufidMapper.insert(ufid);

        long id = ufid.getFid();

        Foid foid = new Foid();
        foid.setFid(id);
        foid.setType(1);
        //foid.setCreateTime(LocalDateTime.now());
        //foid.setModifyTime(LocalDateTime.now());
        foid.setOid(pid);
        foidMapper.insert(foid);
        return ufid.getFid();
    }

    @Override
    public void replaceScatterChart(ScatterPlotDto chart) {
        QueryWrapper<Foid> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("fid", chart.getId());
        List<Foid> foids = foidMapper.selectList(wrapper1);
        if (foids.size() == 0) {
            throw new MyException(ResultEnum.DATA_ERROR, "文件id(fid)不存在");
        }
        Foid foid = foids.get(0);
        long pid = foid.getOid();

        ScatterPlotTemplateDto template = chart.getScatterPlot();
        DataSetDto dataSet = chart.getData();
        templateService.replaceScatterChartTemplate(template);
        dataSetService.replaceDataset(dataSet);

        Upid upidUpdate = new Upid();
        upidUpdate.setPName(chart.getName());
        UpdateWrapper<Upid> wrapper2 = new UpdateWrapper<>();
        wrapper2.eq("pid", pid);
        upidMapper.update(upidUpdate, wrapper2);

        Pdata pdata = new Pdata();
        pdata.setCid(chart.getXid());
        pdata.setXlabel(chart.getXlabel());
        pdata.setYlabel(chart.getYlabel());
        pdata.setXbegin(chart.getXbegin());
        pdata.setYbegin(chart.getYbegin());

        pdata.setWheight(chart.getLength());
        pdata.setWwidth(chart.getWidth());

        UpdateWrapper<Pdata> wrapper3 = new UpdateWrapper<>();
        wrapper3.eq("pid", pid);
        pdataMapper.update(pdata, wrapper3);
        //Py py = new Py();
        //py.setPid(pid);
        QueryWrapper<Py> wrapper6 = new QueryWrapper<>();
        wrapper6.eq("pid", pid);
        pyMapper.delete(wrapper6);
        List<Long> ldata = chart.getYid();
        List<Py> newldata = new ArrayList<>();
        for (int i = 0; i < ldata.size(); i++) {
            Py py = new Py();
            py.setPid(pid);
            py.setNo(i);
            py.setCid(ldata.get(i));
            newldata.add(py);
        }
        pyMapper.insertBatchSomeColumn(newldata);

        Ufid ufid = new Ufid();
        ufid.setFname(chart.getName());
        UpdateWrapper<Ufid> wrapper4 = new UpdateWrapper<>();
        wrapper4.eq("fid", chart.getId());
        ufidMapper.update(ufid, wrapper4);

        Foid foid2 = new Foid();
        //foid2.setModifyTime(LocalDateTime.now());
        UpdateWrapper<Foid> wrapper5 = new UpdateWrapper<>();
        wrapper5.eq("fid", chart.getId());
        foidMapper.update(foid2, wrapper5);
    }
}

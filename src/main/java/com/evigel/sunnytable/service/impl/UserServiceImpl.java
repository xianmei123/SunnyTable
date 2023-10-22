package com.evigel.sunnytable.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.evigel.sunnytable.dto.BarChartTemplateDto;
import com.evigel.sunnytable.dto.FanChartTemplateDto;
import com.evigel.sunnytable.dto.FileDto;
import com.evigel.sunnytable.dto.LineChartTemplateDto;
import com.evigel.sunnytable.dto.ScatterPlotTemplateDto;
import com.evigel.sunnytable.dto.TemplateInfoDto;
import com.evigel.sunnytable.entity.Foid;
import com.evigel.sunnytable.entity.Udid;
import com.evigel.sunnytable.entity.Ufid;
import com.evigel.sunnytable.entity.Ulog;
import com.evigel.sunnytable.entity.Upid;
import com.evigel.sunnytable.mapper.FoidMapper;
import com.evigel.sunnytable.mapper.UdidMapper;
import com.evigel.sunnytable.mapper.UfidMapper;
import com.evigel.sunnytable.mapper.UlogMapper;
import com.evigel.sunnytable.service.IFileService;
import com.evigel.sunnytable.service.ITemplateService;
import com.evigel.sunnytable.service.IUserService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author evigel
 * @since 2021-04-28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {


    @Autowired
    private UfidMapper ufidMapper;

    @Autowired
    private UdidMapper udidMapper;

    @Autowired
    private FoidMapper foidMapper;

    @Autowired
    private UlogMapper ulogMapper;


    @Autowired
    private IFileService fileService;

    @Autowired
    private ITemplateService templateService;

    @Override
    public long login(String uid) {
        QueryWrapper<Ulog> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid);
        wrapper.ge("logIo", "2021-06-10 00:00:00");
        QueryWrapper<Ulog> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("uid",uid);
        wrapper1.ge("logIo", "2021-06-14 00:00:00");
        List<Ulog> ulogs1 = ulogMapper.selectList(wrapper1);
        List<Ulog> ulogs = ulogMapper.selectList(wrapper);
        //List<Ufid> ufids = ufidMapper.selectList(wrapper);
        long fid = 0;
        //System.out.println("size"+ufids.size());
        if (ulogs.size() == 0) {
            fid = fileService.createRoot(uid);
            setDefaultTemplate(uid, 0);
        }
        else if (ulogs1.size() == 0) {
            //更新用
            //updateDefaultTemplate();
            setDefaultTemplate(uid, 1);
            Udid udidroot = new Udid();
            udidroot.setUid(uid);
            udidroot.setType(4);
            QueryWrapper<Udid> wrapper4 = new QueryWrapper<>(udidroot);
            Udid udid = udidMapper.selectOne(wrapper4);

            Foid foidroot = new Foid();
            foidroot.setOid(udid.getDid());
            QueryWrapper<Foid> wrapper5 = new QueryWrapper<>(foidroot);
            Foid foidr = foidMapper.selectOne(wrapper5);
            fid = foidr.getFid();
        }
        else {
            Udid udidroot = new Udid();
            udidroot.setUid(uid);
            udidroot.setType(4);
            QueryWrapper<Udid> wrapper4 = new QueryWrapper<>(udidroot);
            Udid udid = udidMapper.selectOne(wrapper4);

            Foid foidroot = new Foid();
            foidroot.setOid(udid.getDid());
            QueryWrapper<Foid> wrapper5 = new QueryWrapper<>(foidroot);
            Foid foidr = foidMapper.selectOne(wrapper5);
            fid = foidr.getFid();

        }

        Ulog ulog = new Ulog(uid, LocalDateTime.now(), 0);
        ulogMapper.insert(ulog);


        return fid;
    }

    @Override
    public void logout(String uid) {
        Ulog ulog = new Ulog(uid, LocalDateTime.now(), 1);
        ulogMapper.insert(ulog);
    }

    public void setDefaultTemplate(String userId, int flag) {

        List<String> colors = new ArrayList<>();
        List<Integer> points = new ArrayList<>();
        List<Integer> lines = new ArrayList<>();
        //Object object = null;

        colors.add("#c1232b");
        colors.add("#27727b");
        colors.add("#fcce10");
        colors.add("#e87c25");
        colors.add("#b5c334");
        colors.add("#fe8463");
        colors.add("#9bca63");
        colors.add("#fad860");
        colors.add("#f3a43b");
        colors.add("#60c0dd");
        colors.add("#d7504b");
        colors.add("#c6e579");
        colors.add("#f4e001");
        colors.add("#f0805a");
        colors.add("#26c0c0");
        BarChartTemplateDto barChartTemplateDto = new BarChartTemplateDto(null, "柱状图默认模板", userId,
                0.25, 0.0, colors, "true", "true", 14,
                "30%,null,80%,null,vertical", "#1e90ff", "true", "false false",
                "false false", "false false", 20, 0,
                "false false", "");

        BarChartTemplateDto jj = new BarChartTemplateDto(null, "聚焦柱状图模板", userId,
                0.25, 0.0, colors, "true", "true", 14,
                "30%,null,80%,null,vertical", "#1e90ff", "true",
                "true true true true true true true true true true true true true true true true",
                "false false", "false false", 20, 0,
                "false false", "");

        BarChartTemplateDto fc = new BarChartTemplateDto(null, "分层式柱状图模板", userId,
                0.25, 0.0, colors, "true", "true", 14,
                "30%,null,80%,null,vertical", "#1e90ff", "true", "false false",
                "false false", "false false", 20, 0,
                "false false", "1 1 1 2 2 2 3 3 3 4 4 4 5 5 5");


        colors = new ArrayList<>();

        colors.add("#c1232b");
        colors.add("#27727b");
        colors.add("#fcce10");
        colors.add("#e87c25");
        colors.add("#b5c334");
        colors.add("#fe8463");
        colors.add("#9bca63");
        colors.add("#fad860");
        colors.add("#f3a43b");
        colors.add("#60c0dd");
        colors.add("#d7504b");
        colors.add("#c6e579");
        colors.add("#f4e001");
        colors.add("#f0805a");
        colors.add("#26c0c0");

        LineChartTemplateDto lineChartTemplateDto = new LineChartTemplateDto(null, "折线图默认模板", userId,
                "20",points, lines, colors, "true", 14,
                "30%,null,80%,null,vertical", "#1e90ff", "true",
                "true", "true", "#c1232b #27727b #fcce10 #e87c25 #b5c334 #fe8463 #9bca63 #fad860 #f3a43b #60c0dd #d7504b #c6e579 #f4e001 #f0805a #26c0c0",
                "false false", "false false",
                "false false", "false false", 20, 0, "false false",
                "false", "false", "false", "#4d80e6", "0.1 0.5",
                "0.1 0.5", "0.1 0.5", "");

        LineChartTemplateDto jb = new LineChartTemplateDto(null, "渐变折线图模板", userId,
                "20",points, lines, colors, "true", 14,
                "30%,null,80%,null,vertical", "#1e90ff", "true",
                "true", "true", "#c1232b #27727b #fcce10 #e87c25 #b5c334 #fe8463 #9bca63 #fad860 #f3a43b #60c0dd #d7504b #c6e579 #f4e001 #f0805a #26c0c0",
                "false false", "false false",
                "false false", "false false", 20, 0, "false false",
                "true", "true", "false", "#4d80e6", "0.1 0.5",
                "0.1 0.5", "0.1 0.5", "");

        LineChartTemplateDto mj = new LineChartTemplateDto(null, "面积折线图模板", userId,
                "20",points, lines, colors, "true", 14,
                "30%,null,80%,null,vertical", "#1e90ff", "true",
                "true", "true", "#c1232b #27727b #fcce10 #e87c25 #b5c334 #fe8463 #9bca63 #fad860 #f3a43b #60c0dd #d7504b #c6e579 #f4e001 #f0805a #26c0c0",
                "true true true true true true true true true true true true true true true true", "false false",
                "false false", "false false", 20, 0, "false false",
                "false", "false", "false", "#4d80e6", "0.1 0.5",
                "0.1 0.5", "0.1 0.5", "");

        LineChartTemplateDto dd = new LineChartTemplateDto(null, "堆叠折线图模板", userId,
                "20",points, lines, colors, "true", 14,
                "30%,null,80%,null,vertical", "#1e90ff", "true",
                "true", "true", "#c1232b #27727b #fcce10 #e87c25 #b5c334 #fe8463 #9bca63 #fad860 #f3a43b #60c0dd #d7504b #c6e579 #f4e001 #f0805a #26c0c0",
                "false false", "false false",
                "false false", "false false", 20, 0, "false false",
                "false", "false", "false", "#4d80e6", "0.1 0.5",
                "0.1 0.5", "0.1 0.5", "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1");

        colors = new ArrayList<>();
        colors.add("#c1232b");
        colors.add("#27727b");
        colors.add("#fcce10");
        colors.add("#e87c25");
        colors.add("#b5c334");
        colors.add("#fe8463");
        colors.add("#9bca63");
        colors.add("#fad860");
        colors.add("#f3a43b");
        colors.add("#60c0dd");
        colors.add("#d7504b");
        colors.add("#c6e579");
        colors.add("#f4e001");
        colors.add("#f0805a");
        colors.add("#26c0c0");
        ScatterPlotTemplateDto scatterPlotTemplateDto = new ScatterPlotTemplateDto(null, "散点图默认模板", userId,
                points, colors, "true", "false", "false", 12, "30%,null,80%,null,vertical",
                "#0000ff", "true", "false", 0);

//        ScatterPlotTemplateDto scatterPlotTemplateDto = new ScatterPlotTemplateDto(null, "散点图默认模板", userId,
//                points, colors, "true", "false", "false", 12, "30%,null,80%,null,vertical",
//                "#0000ff", "true", "false", 0);
//
//        ScatterPlotTemplateDto scatterPlotTemplateDto = new ScatterPlotTemplateDto(null, "散点图默认模板", userId,
//                points, colors, "true", "false", "false", 12, "30%,null,80%,null,vertical",
//                "#0000ff", "true", "false", 0);


        colors = new ArrayList<>();
        colors.add("#c1232b");
        colors.add("#27727b");
        colors.add("#fcce10");
        colors.add("#e87c25");
        colors.add("#b5c334");
        colors.add("#fe8463");
        colors.add("#9bca63");
        colors.add("#fad860");
        colors.add("#f3a43b");
        colors.add("#60c0dd");
        colors.add("#d7504b");
        colors.add("#c6e579");
        colors.add("#f4e001");
        colors.add("#f0805a");
        colors.add("#26c0c0");
        FanChartTemplateDto fanChartTemplateDto = new FanChartTemplateDto(null, "扇形图默认模板", userId,
                "40% 80%", 2, colors, "true", "true", 20, 10,
                "30%,null,80%,null,vertical", "#b22222", "true", 8, "false", "false", "radius");

        FanChartTemplateDto nd = new FanChartTemplateDto(null, "南丁格尔图模板", userId,
                "40% 80%", 2, colors, "true", "true", 20, 10,
                "30%,null,80%,null,vertical", "#b22222", "true", 8, "false", "true", "radius");

        FanChartTemplateDto yx = new FanChartTemplateDto(null, "圆环扇形图模板", userId,
                "40% 80%", 2, colors, "true", "true", 20, 10,
                "30%,null,80%,null,vertical", "#b22222", "true", 8, "true", "false", "radius");


        templateService.saveFanChartTemplate(nd);
        templateService.saveFanChartTemplate(yx);
        templateService.saveBarChartTemplate(jj);
        templateService.saveBarChartTemplate(fc);
        templateService.saveLineChartTemplate(jb);
        templateService.saveLineChartTemplate(mj);
        templateService.saveLineChartTemplate(dd);

        if (flag == 0) {
            templateService.saveScatterChartTemplate(scatterPlotTemplateDto);
            templateService.saveLineChartTemplate(lineChartTemplateDto);
            templateService.saveFanChartTemplate(fanChartTemplateDto);
            templateService.saveBarChartTemplate(barChartTemplateDto);
        }

       
    }

    public void updateDefaultTemplate(String userId) {

        List<String> colors = new ArrayList<>();
        List<Integer> points = new ArrayList<>();
        List<Integer> lines = new ArrayList<>();
        //Object object = null;

        colors.add("#b22222");
        colors.add("#ba55d3");
        BarChartTemplateDto barChartTemplateDto = new BarChartTemplateDto(null, "柱状图默认模板", userId,
                0.25, 0.0, colors, "true", "true", 14,
                "30%,null,80%,null,vertical", "#1e90ff", "true", "false false",
                "false false", "false false", 20, 0,
                "false false", "");

        colors = new ArrayList<>();

        colors.add("#c1232b");
        colors.add("#27727b");
        colors.add("#fcce10");
        colors.add("#e87c25");
        colors.add("#b5c334");
        colors.add("#fe8463");
        colors.add("#9bca63");
        colors.add("#fad860");
        colors.add("#f3a43b");
        colors.add("#60c0dd");
        colors.add("#d7504b");
        colors.add("#c6e579");
        colors.add("#f4e001");
        colors.add("#f0805a");
        colors.add("#26c0c0");

        LineChartTemplateDto lineChartTemplateDto = new LineChartTemplateDto(null, "折线图默认模板", userId,
                "20",points, lines, colors, "true", 14,
                "30%,null,80%,null,vertical", "#1e90ff", "true",
                "true", "true", "", "false false", "false false",
                "false false", "false false", 20, 0, "false false",
                "false", "false", "false", "#4d80e6", "0.1 0.5",
                "0.1 0.5", "0.1 0.5", "");

        colors = new ArrayList<>();
        colors.add("#0000ff");
        ScatterPlotTemplateDto scatterPlotTemplateDto = new ScatterPlotTemplateDto(null, "散点图默认模板", userId,
                points, colors, "true", "true", "false", 12, "30%,null,80%,null,vertical",
                "#0000ff", "true", "false", 0);


        colors = new ArrayList<>();
        colors.add("#b22222");
        colors.add("#8b008b");
        FanChartTemplateDto fanChartTemplateDto = new FanChartTemplateDto(null, "扇形图默认模板", userId,
                "40% 80%", 2, colors, "true", "true", 20, 10,
                "30%,null,80%,null,vertical", "#b22222", "true", 8, "false", "false", "radius");

        templateService.replaceScatterChartTemplate(scatterPlotTemplateDto);
        templateService.replaceLineChartTemplate(lineChartTemplateDto);
        templateService.replaceFanChartTemplate(fanChartTemplateDto);
        templateService.replaceBarChartTemplate(barChartTemplateDto);

    }


}

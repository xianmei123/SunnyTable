package com.evigel.sunnytable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.evigel.sunnytable.dto.BarChartTemplateDto;
import com.evigel.sunnytable.dto.FanChartTemplateDto;
import com.evigel.sunnytable.dto.LineChartTemplateDto;
import com.evigel.sunnytable.dto.ScatterPlotTemplateDto;
import com.evigel.sunnytable.dto.TemplateInfoDto;
import com.evigel.sunnytable.entity.Mcolor;
import com.evigel.sunnytable.entity.Mline;
import com.evigel.sunnytable.entity.Mpoint;
import com.evigel.sunnytable.entity.Umid;

import java.util.List;

public interface ITemplateService {
    List<TemplateInfoDto> displayTemplate(String uid, int maxNum);

    BarChartTemplateDto openBarChartTemplate(long fid);

    long saveBarChartTemplate(BarChartTemplateDto template);

    LineChartTemplateDto openLineChartTemplate(long fid);

    long saveLineChartTemplate(LineChartTemplateDto template);

    FanChartTemplateDto openFanChartTemplate(long fid);

    long saveFanChartTemplate(FanChartTemplateDto template);

    ScatterPlotTemplateDto openScatterChartTemplate(long fid);

    long saveScatterChartTemplate(ScatterPlotTemplateDto template);

    void replaceScatterChartTemplate(ScatterPlotTemplateDto template);

    void replaceLineChartTemplate(LineChartTemplateDto template);

    void replaceFanChartTemplate(FanChartTemplateDto template);

    void replaceBarChartTemplate(BarChartTemplateDto template);

    Long shareTemplate(String uid, Long fid);

    void updateScatter(ScatterPlotTemplateDto template);

    void updateFan(FanChartTemplateDto template);

    void updateLine(LineChartTemplateDto template);

    void updateBar(BarChartTemplateDto template);
}

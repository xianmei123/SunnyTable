package com.evigel.sunnytable.service;

import com.evigel.sunnytable.dto.BarChartDto;
import com.evigel.sunnytable.dto.FanChartDto;
import com.evigel.sunnytable.dto.LineChartDto;
import com.evigel.sunnytable.dto.ScatterPlotDto;

public interface IChartService {
    BarChartDto openBarChart(long fid);
    Long saveBarChart(BarChartDto chart);
    void replaceBarChart(BarChartDto chart);
    FanChartDto openFanChart(long fid);
    Long saveFanChart(FanChartDto chart);
    void replaceFanChart(FanChartDto chart);
    LineChartDto openLineChart(long fid);
    Long saveLineChart(LineChartDto chart);
    void replaceLineChart(LineChartDto chart);
    ScatterPlotDto openScatterChart(long fid);
    Long saveScatterChart(ScatterPlotDto chart);
    void replaceScatterChart(ScatterPlotDto chart);
}

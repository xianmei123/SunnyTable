package com.evigel.sunnytable.controller;

import com.evigel.sunnytable.dto.BarChartDto;
import com.evigel.sunnytable.dto.FanChartDto;
import com.evigel.sunnytable.dto.LineChartDto;
import com.evigel.sunnytable.dto.ScatterPlotDto;
import com.evigel.sunnytable.exception.MyException;
import com.evigel.sunnytable.exception.ResultEnum;
import com.evigel.sunnytable.service.IChartService;
import com.evigel.sunnytable.service.impl.ChartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chart")
public class ChartController {

    @Autowired
    private IChartService chartService;

    @RequestMapping("/barchart/open/{fid}")
    public ResponseEntity<BarChartDto> openBarChart(@PathVariable("fid") long fid) {
        BarChartDto chart = chartService.openBarChart(fid);
        return ResponseEntity.ok().body(chart);
    }

    @RequestMapping("/fanchart/open/{fid}")
    public ResponseEntity<FanChartDto> openFanChart(@PathVariable("fid") long fid) {
        FanChartDto chart = chartService.openFanChart(fid);
        return ResponseEntity.ok().body(chart);
    }

    @RequestMapping("/linechart/open/{fid}")
    public ResponseEntity<LineChartDto> openLineChart(@PathVariable("fid") long fid) {
        LineChartDto chart = chartService.openLineChart(fid);
        return ResponseEntity.ok().body(chart);
    }

    @RequestMapping("/scatterplot/open/{fid}")
    public ResponseEntity<ScatterPlotDto> openScatterPlot(@PathVariable("fid") long fid) {
        ScatterPlotDto chart = chartService.openScatterChart(fid);
        return ResponseEntity.ok().body(chart);
    }


    @RequestMapping("/barchart/save")
    public Long saveBarChart(@RequestBody BarChartDto chart) {

        return chartService.saveBarChart(chart);
        //throw new MyException(ResultEnum.SUCCESS, "保存成功");
    }

    @RequestMapping("/fanchart/save")
    public Long saveFanChart(@RequestBody FanChartDto chart) {
        return chartService.saveFanChart(chart);
        //throw new MyException(ResultEnum.SUCCESS, "保存成功");
    }

    @RequestMapping("/linechart/save")
    public Long saveLineChart(@RequestBody LineChartDto chart) {
        return chartService.saveLineChart(chart);
        //throw new MyException(ResultEnum.SUCCESS, "保存成功");
    }

    @RequestMapping("/scatterplot/save")
    public Long saveScatterPlot(@RequestBody ScatterPlotDto chart) {
        return chartService.saveScatterChart(chart);
        //throw new MyException(ResultEnum.SUCCESS, "保存成功");
    }

    @RequestMapping("/barchart/replace")
    public void replaceBarChart(@RequestBody BarChartDto chart) {
        chartService.replaceBarChart(chart);
        throw new MyException(ResultEnum.SUCCESS, "更新成功");
    }

    @RequestMapping("/fanchart/replace")
    public void replaceFanChart(@RequestBody FanChartDto chart) {
        chartService.replaceFanChart(chart);
        throw new MyException(ResultEnum.SUCCESS, "更新成功");
    }

    @RequestMapping("/linechart/replace")
    public void replaceLineChart(@RequestBody LineChartDto chart) {
        chartService.replaceLineChart(chart);
        throw new MyException(ResultEnum.SUCCESS, "更新成功");
    }

    @RequestMapping("/scatterplot/replace")
    public void replaceScatterPlot(@RequestBody ScatterPlotDto chart) {
        chartService.replaceScatterChart(chart);
        throw new MyException(ResultEnum.SUCCESS, "更新成功");
    }


}

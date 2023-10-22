package com.evigel.sunnytable.controller;

import com.evigel.sunnytable.dto.BarChartTemplateDto;
import com.evigel.sunnytable.dto.FanChartTemplateDto;
import com.evigel.sunnytable.dto.LineChartTemplateDto;
import com.evigel.sunnytable.dto.ScatterPlotTemplateDto;
import com.evigel.sunnytable.dto.TemplateInfoDto;
import com.evigel.sunnytable.entity.Umid;
import com.evigel.sunnytable.exception.MyException;
import com.evigel.sunnytable.exception.ResultEnum;
import com.evigel.sunnytable.service.ITemplateService;
import com.evigel.sunnytable.service.impl.FileServiceImpl;
import com.evigel.sunnytable.service.impl.TemplateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    private ITemplateService templateService;

    @RequestMapping("/chart/share/{uid}/{fid}")
    public void shareTemplate(@PathVariable("uid") String uid,
                                @PathVariable("fid") long fid){
        Long fid2 = templateService.shareTemplate(uid,fid);

//        List<TemplateInfoDto> list2 = templateService.displayFanTemplate(uid,maxNum);
//        List<TemplateInfoDto> list3 = templateService.displayBarTemplate(uid,maxNum);
//        List<TemplateInfoDto> list4 = templateService.displayBarTemplate(uid,maxNum);
        throw new MyException(ResultEnum.SUCCESS, "分享成功");
    }


    @RequestMapping("/chart/display/{uid}/{maxNum}")
    public ResponseEntity<List<TemplateInfoDto>> displayTemplate(@PathVariable("uid") String uid,
                                                                             @PathVariable("maxNum") int maxNum){
        List<TemplateInfoDto> list1 = templateService.displayTemplate(uid,maxNum);

//        List<TemplateInfoDto> list2 = templateService.displayFanTemplate(uid,maxNum);
//        List<TemplateInfoDto> list3 = templateService.displayBarTemplate(uid,maxNum);
//        List<TemplateInfoDto> list4 = templateService.displayBarTemplate(uid,maxNum);
        return ResponseEntity.ok().body(list1);
    }

//    @RequestMapping("/fanchart/display/{uid}/{type}/{maxnum}")
//    public ResponseEntity<List<FanChartTemplateDto>> displayFanchartTemplate(@PathVariable("uid") String uid,
//                                                                             @PathVariable("maxnum") int maxNum){
//        List<FanChartTemplateDto> list = templateService.displayTemplate(uid,maxNum);
//        return ResponseEntity.ok().body(list);
//    }
//
//    @RequestMapping("/linechart/display/{uid}/{type}/{maxnum}")
//    public ResponseEntity<List<LineChartTemplateDto>> displayLinechartTemplate(@PathVariable("uid") String uid,
//                                                                               @PathVariable("maxnum") int maxNum){
//        List<LineChartTemplateDto> list = fileService.displayTemplate(uid,maxNum);
//        return ResponseEntity.ok().body(list);
//    }
//
//    @RequestMapping("/scatterplot/display/{uid}/{type}/{maxnum}")
//    public ResponseEntity<List<ScatterPlotTemplateDto>> displayScatterplotTemplate(@PathVariable("uid") String uid,
//                                                                                   @PathVariable("maxnum") int maxNum){
//        List<ScatterPlotTemplateDto> list = fileService.displayTemplate(uid,maxNum);
//        return ResponseEntity.ok().body(list);
//    }

    @RequestMapping("/barchart/open/{fid}")
    public ResponseEntity<BarChartTemplateDto> openBarchatTemplate(@PathVariable("fid") long fid){
        BarChartTemplateDto template = templateService.openBarChartTemplate(fid);
        return ResponseEntity.ok().body(template);
    }

    @RequestMapping("/fanchart/open/{fid}")
    public ResponseEntity<FanChartTemplateDto> openFanchartTemplate(@PathVariable("fid") long fid){
        FanChartTemplateDto template = templateService.openFanChartTemplate(fid);
        return ResponseEntity.ok().body(template);
    }

    @RequestMapping("/linechart/open/{fid}")
    public ResponseEntity<LineChartTemplateDto> openLinechartTemplate(@PathVariable("fid") long fid){
        LineChartTemplateDto template = templateService.openLineChartTemplate(fid);
        return ResponseEntity.ok().body(template);
    }

    @RequestMapping("/scatterplot/open/{fid}")
    public ResponseEntity<ScatterPlotTemplateDto> openScatterplotTemplate(@PathVariable("fid") long fid){
        ScatterPlotTemplateDto template = templateService.openScatterChartTemplate(fid);
        return ResponseEntity.ok().body(template);
    }

    @RequestMapping("/barchart/save")
    public Long saveBarchatTemplate(@RequestBody BarChartTemplateDto template){
        return templateService.saveBarChartTemplate(template);
        //throw new MyException(ResultEnum.SUCCESS, "保存成功");
    }

    @RequestMapping("/fanchart/save")
    public Long saveFanchatTemplate(@RequestBody FanChartTemplateDto template){
        return templateService.saveFanChartTemplate(template);
        //throw new MyException(ResultEnum.SUCCESS, "保存成功");
    }

    @RequestMapping("/linechart/save")
    public Long saveLinechatTemplate(@RequestBody LineChartTemplateDto template){
        return templateService.saveLineChartTemplate(template);
        //throw new MyException(ResultEnum.SUCCESS, "保存成功");
    }

    @RequestMapping("/scatterplot/save")
    public Long saveScatterplotTemplate(@RequestBody ScatterPlotTemplateDto template){
        return templateService.saveScatterChartTemplate(template);
        //throw new MyException(ResultEnum.SUCCESS, "保存成功");
    }

    @RequestMapping("/barchart/replace")
    public void replaceBarchatTemplate(@RequestBody BarChartTemplateDto template){
        templateService.replaceBarChartTemplate(template);
        throw new MyException(ResultEnum.SUCCESS, "更新成功");
    }

    @RequestMapping("/fanchart/replace")
    public void replaceFanchatTemplate(@RequestBody FanChartTemplateDto template){
        templateService.replaceFanChartTemplate(template);
        throw new MyException(ResultEnum.SUCCESS, "更新成功");
    }

    @RequestMapping("/linechart/replace")
    public void replaceLinechatTemplate(@RequestBody LineChartTemplateDto template){
        templateService.replaceLineChartTemplate(template);
        throw new MyException(ResultEnum.SUCCESS, "更新成功");
    }

    @RequestMapping("/scatterplot/replace")
    public void replaceScatterplotTemplate(@RequestBody ScatterPlotTemplateDto template){
        templateService.replaceScatterChartTemplate(template);
        throw new MyException(ResultEnum.SUCCESS, "更新成功");
    }


    @RequestMapping("/bar/updateAll")
    public void updateBar(@RequestBody BarChartTemplateDto template){
        templateService.updateBar(template);
        throw new MyException(ResultEnum.SUCCESS, "更新成功");
    }

    @RequestMapping("/line/updateAll")
    public void updateLine(@RequestBody LineChartTemplateDto template){
        templateService.updateLine(template);
        throw new MyException(ResultEnum.SUCCESS, "更新成功");
    }

    @RequestMapping("/fan/updateAll")
    public void updateFan(@RequestBody FanChartTemplateDto template){
        templateService.updateFan(template);
        throw new MyException(ResultEnum.SUCCESS, "更新成功");
    }

    @RequestMapping("/scatter/updateAll")
    public void updateScatter(@RequestBody ScatterPlotTemplateDto template){
        templateService.updateScatter(template);
        throw new MyException(ResultEnum.SUCCESS, "更新成功");
    }

}

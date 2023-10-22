package com.evigel.sunnytable.controller;

import com.evigel.sunnytable.dto.DataSetDto;

import com.evigel.sunnytable.exception.MyException;
import com.evigel.sunnytable.exception.ResultEnum;
import com.evigel.sunnytable.service.IDataSetService;
import com.evigel.sunnytable.service.impl.DataSetServiceImpl;
import com.evigel.sunnytable.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/data")
public class DataSetController {

    @Autowired
    private IDataSetService dataSetService;
    @RequestMapping("/open/{fid}")
    public ResponseEntity<DataSetDto> openDataset(@PathVariable("fid") long fid){
        DataSetDto data = dataSetService.openDataset(fid);
        return ResponseEntity.ok().body(data);
    }


    @RequestMapping("/save")
    public void saveDataset(@RequestBody DataSetDto data){
        dataSetService.saveDataset(data);
        throw new MyException(ResultEnum.SUCCESS, "保存成功");
    }

    @RequestMapping("/replace")
    public void replaceDataset(@RequestBody DataSetDto data){
        dataSetService.replaceDataset(data);
        throw new MyException(ResultEnum.SUCCESS, "更新成功");
    }


    @RequestMapping("/export/{uid}/{type}")
    public String exportDataset(@PathVariable("type") int type, @RequestBody DataSetDto data, @PathVariable String uid) {    //TODO 返回格式
        dataSetService.exportDataset(uid, type,data);
        throw new MyException(ResultEnum.SUCCESS, "导出成功");
    }

    @RequestMapping("/getFile/{uid}/{type}")
    public ResponseEntity<byte[]> getFile(@PathVariable("type") int type, @PathVariable String uid) throws Exception {    //TODO 返回格式
        return Tool.getFile(uid, type);
    }

    @RequestMapping("/readFile/{uid}")
    public ResponseEntity<DataSetDto> readFile(@PathVariable String uid, HttpServletRequest request) {    //TODO 返回格式
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
        if (file == null) {
            throw new MyException(ResultEnum.DATA_ERROR, "没有传入文件");
        }
        DataSetDto dataSetDto = Tool.readExcel(file);
        dataSetDto.setUserId(uid);
        return ResponseEntity.ok().body(dataSetDto);
    }

//    @RequestMapping("/export")
//    public void exportDataset() throws IOException {    //TODO 返回格式
//        DataSetDto dataSetDto = new DataSetDto();
//        dataSetDto.setId((long) 1);
//        dataSetDto.setName("gg");
//        dataSetDto.setUserId("sb");
//        List<LineDataDto> columns = new ArrayList<>();
//        List<String> column = new ArrayList<>();
//        column.add("a");
//        column.add("c");
//        column.add("d");
//        LineDataDto lineDataDto = new LineDataDto("za", column);
//        columns.add(lineDataDto);
//        column = new ArrayList<>();
//        column.add("e");
//        column.add("f");
//        column.add("g");
//        lineDataDto = new LineDataDto("zb", column);
//        columns.add(lineDataDto);
//
//        dataSetDto.setDataArray(columns);
//        dataSetService.exportDataset(1, dataSetDto);
//    }
}

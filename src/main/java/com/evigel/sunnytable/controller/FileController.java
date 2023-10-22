package com.evigel.sunnytable.controller;

import com.evigel.sunnytable.dto.FileDto;
import com.evigel.sunnytable.exception.MyException;
import com.evigel.sunnytable.exception.ResultEnum;
import com.evigel.sunnytable.service.IDataSetService;
import com.evigel.sunnytable.service.IFileService;
import com.evigel.sunnytable.service.impl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private IFileService fileService;

    @RequestMapping("/dir/open/{uid}/{fid}")
    public ResponseEntity<List<FileDto>> openDir(@PathVariable("uid") String userId,
                                                 @PathVariable("fid") long dirId) {
        List<FileDto> fileDtoList = fileService.openDir(userId,dirId);
        return ResponseEntity.ok().body(fileDtoList);
    }

    @RequestMapping("/dir/create/{uid}/{fid}/{name}")
    public ResponseEntity<FileDto> createDir(@PathVariable("uid") String userId,
                                          @PathVariable("fid") long dirId,
                                          @PathVariable("name") String name){
        FileDto file = fileService.createDir(userId,dirId,name);
        return ResponseEntity.ok().body(file);
    }

    @RequestMapping("/dir/remove/{srcfid}")
    public void remove(@PathVariable("srcfid") long srcFid){
        fileService.remove(srcFid);
        throw new MyException(ResultEnum.SUCCESS, "删除成功");
    }


    @RequestMapping("/dir/move/{srcfid}/{dstfid}")
    public void moveDir(@PathVariable("srcfid") long srcFid,
                        @PathVariable("dstfid") long dstFid){
        fileService.move(srcFid,dstFid);
        throw new MyException(ResultEnum.SUCCESS, "移动成功");
    }

    @RequestMapping("/dir/rename/{srcfid}/{name}")
    public void rename(@PathVariable("srcfid") long srcFid,
                        @PathVariable("name") String name){
        fileService.rename(srcFid,name);
        throw new MyException(ResultEnum.SUCCESS, "重命名成功");
    }






}

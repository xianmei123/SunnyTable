package com.evigel.sunnytable.controller;

import com.evigel.sunnytable.dto.BarChartDto;
import com.evigel.sunnytable.dto.BillDto;
import com.evigel.sunnytable.dto.QueryInfoDto;
import com.evigel.sunnytable.dto.QueryTypeInfoDto;
import com.evigel.sunnytable.exception.MyException;
import com.evigel.sunnytable.exception.ResultEnum;
import com.evigel.sunnytable.service.IBillService;
import com.evigel.sunnytable.service.IChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private IBillService billService;

    @RequestMapping("/add")
    public Long addBill(@RequestBody BillDto bill) {
        return billService.addBill(bill);
    }

    @RequestMapping("/query/time")
    public ResponseEntity<List<BillDto>> queryTime(@RequestBody QueryInfoDto query) {
        List<BillDto> list = billService.queryTime(query);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping("/query/type")
    public ResponseEntity<List<BillDto>> queryType(@RequestBody QueryTypeInfoDto query) {
        List<BillDto> list = billService.queryType(query);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping("/query/alltype/{uid}")
    public ResponseEntity<List<String>> queryAllType(@PathVariable("uid") String uid) {
        List<String> list = billService.queryAllType(uid);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping("/replace")
    public void replaceBill(@RequestBody BillDto bill) {
        billService.replaceBill(bill);
        throw new MyException(ResultEnum.SUCCESS, "替换成功");
    }

    @RequestMapping("/delete/{bid}")
    public void deleteBill(@PathVariable("bid") Long bid) {
        billService.deleteBill(bid);
        throw new MyException(ResultEnum.SUCCESS, "删除成功");

    }
}

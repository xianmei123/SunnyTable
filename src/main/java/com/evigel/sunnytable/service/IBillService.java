package com.evigel.sunnytable.service;

import com.evigel.sunnytable.dto.BillDto;
import com.evigel.sunnytable.dto.QueryInfoDto;
import com.evigel.sunnytable.dto.QueryTypeInfoDto;
import com.evigel.sunnytable.entity.Ubid;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

public interface IBillService {

    Long addBill(BillDto bill);
    List<BillDto> queryTime(QueryInfoDto query);
    List<BillDto> queryType(QueryTypeInfoDto query);
    void replaceBill(BillDto bill);
    void deleteBill(Long bid);
    List<String> queryAllType(String uid);
}

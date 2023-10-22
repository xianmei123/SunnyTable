package com.evigel.sunnytable.mapper;

import com.evigel.sunnytable.dto.BillDto;
import com.evigel.sunnytable.entity.Ubid;
import com.evigel.sunnytable.entity.Udid;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public interface UbidMapper extends MyBaseMapper<Ubid> {
    List<BillDto> getBill(String uid, Date start_time, Date end_time);

    List<BillDto> getBillBeforeTime(String uid, Date end_time);

    List<BillDto> getBillAfterTime(String uid, Date start_time);

    List<BillDto> getBillAll(String uid);

    List<BillDto> getBillType(String uid, Date start_time, Date end_time, String type);

    List<BillDto> getBillBeforeTimeType(String uid, Date end_time, String type);

    List<BillDto> getBillAfterTimeType(String uid, Date start_time, String type);

    List<BillDto> getBillAllType(String uid, String type);

    List<String> getAllType(String uid);

}

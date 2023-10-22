package com.evigel.sunnytable.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.evigel.sunnytable.dto.BillDto;
import com.evigel.sunnytable.dto.QueryInfoDto;
import com.evigel.sunnytable.dto.QueryTypeInfoDto;
import com.evigel.sunnytable.entity.Foid;
import com.evigel.sunnytable.entity.Pdata;
import com.evigel.sunnytable.entity.Ubid;
import com.evigel.sunnytable.entity.Ufid;
import com.evigel.sunnytable.entity.Utid;
import com.evigel.sunnytable.exception.MyException;
import com.evigel.sunnytable.exception.ResultEnum;
import com.evigel.sunnytable.mapper.UbidMapper;
import com.evigel.sunnytable.mapper.UpidMapper;
import com.evigel.sunnytable.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class BillServiceImpl implements IBillService {

    @Autowired
    private UbidMapper ubidMapper;

    @Override
    public Long addBill(BillDto bill) {
        Ubid ubid = new Ubid();
        ubid.setUid(bill.getUserId());
        ubid.setDetail(bill.getDetail());
        ubid.setTime(bill.getTime());
        ubid.setType(bill.getType());
        ubid.setIncome(bill.getIncome());
        ubid.setCost(bill.getCost());
        ubidMapper.insert(ubid);
        return ubid.getBid();
    }

    @Override
    public List<BillDto> queryTime(QueryInfoDto query) {
        String uid = query.getUserId();
        Date startTime = query.getStartTime();
        Date endTime = query.getEndTime();
        List<BillDto> list;
        if (startTime != null && endTime != null)
            list = ubidMapper.getBill(uid, startTime, endTime);
        else if (startTime != null)
            list = ubidMapper.getBillAfterTime(uid, startTime);
        else if (endTime != null)
            list = ubidMapper.getBillBeforeTime(uid, endTime);
        else
            list = ubidMapper.getBillAll(uid);
        return list;
    }

    @Override
    public List<BillDto> queryType(QueryTypeInfoDto query) {
        String uid = query.getUserId();
        Date startTime = query.getStartTime();
        Date endTime = query.getEndTime();
        String type = query.getType();
        List<BillDto> list;
        if (type == null || uid == null)
            throw new MyException(ResultEnum.DATA_ERROR, "值不可为空");

        if (startTime != null && endTime != null)
            list = ubidMapper.getBillType(uid, startTime, endTime, type);
        else if (startTime != null)
            list = ubidMapper.getBillAfterTimeType(uid, startTime, type);
        else if (endTime != null)
            list = ubidMapper.getBillBeforeTimeType(uid, endTime, type);
        else
            list = ubidMapper.getBillAllType(uid, type);
        return list;
    }

    @Override
    public void replaceBill(BillDto bill) {
        QueryWrapper<Ubid> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bid", bill.getId());
        Ubid ubid = ubidMapper.selectOne(queryWrapper);


        if (ubid == null) {
            throw new MyException(ResultEnum.DATA_ERROR, "账单id(bid)不存在");
        }

        Ubid ubid2 = new Ubid();
        ubid2.setDetail(bill.getDetail());
        ubid2.setTime(bill.getTime());
        ubid2.setType(bill.getType());
        ubid2.setIncome(bill.getIncome());
        ubid2.setCost(bill.getCost());

        UpdateWrapper<Ubid> wrapper = new UpdateWrapper<>();
        wrapper.eq("bid", bill.getId());
        ubidMapper.update(ubid2, wrapper);
    }

    @Override
    public void deleteBill(Long bid) {
        QueryWrapper<Ubid> wrapper = new QueryWrapper<>();
        wrapper.eq("bid", bid);
        ubidMapper.delete(wrapper);
    }

    @Override
    public List<String> queryAllType(String uid) {
        List<String> list = ubidMapper.getAllType(uid);
        return list;
    }
}

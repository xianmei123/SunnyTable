package com.evigel.sunnytable.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {
    private Long id;
    private String userId;
    private String detail;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date time;
    private String type;
    private String income;
    private double cost;
}
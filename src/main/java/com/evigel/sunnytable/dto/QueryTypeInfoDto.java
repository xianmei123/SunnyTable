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
public class QueryTypeInfoDto {
    private String userId;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date startTime;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date endTime;
    private String type;
}

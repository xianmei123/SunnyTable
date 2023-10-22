package com.evigel.sunnytable.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {
    private Long id;
    private String name;
    private Integer type;
    private Integer templateType;
    private Timestamp createTime;
}

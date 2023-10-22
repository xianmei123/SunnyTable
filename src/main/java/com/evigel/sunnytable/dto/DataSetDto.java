package com.evigel.sunnytable.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataSetDto {
    //元数据组
    private Long id;
    private String name;
    private String userId;
    private List<LineDataDto> dataArray;
}

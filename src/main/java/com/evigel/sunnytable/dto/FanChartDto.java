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
public class FanChartDto {
    private Long id;
    private String name;
    private String xlabel;
    private String ylabel;
    private Long xid;
    private List<Long> yid;
    private Long xbegin;
    private Long ybegin;
    private double length;
    private double width;
    private FanChartTemplateDto fanChart;
    private DataSetDto data;
}

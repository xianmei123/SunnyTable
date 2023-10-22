package com.evigel.sunnytable.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BarChartDto {
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
    private BarChartTemplateDto barChart;
    private DataSetDto data;


}

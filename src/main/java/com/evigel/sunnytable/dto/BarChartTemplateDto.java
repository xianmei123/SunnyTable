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
public class BarChartTemplateDto {
    private Long id;
    private String name;
    private String userId;
    private double width;
    private double gap;
    private List<String> color;
    private String showDigit;
    private String transpose;
    private int font;
    private String legendPos;
    private String textColor;
    private String isVisible;
    private String showEmphasis;
    private String showMinMarkPoint;
    private String showMaxMarkPoint;
    private Integer markPointSize;
    private Integer markPointStyle;
    private String showAverageMarkLine;
    private String stack;
}

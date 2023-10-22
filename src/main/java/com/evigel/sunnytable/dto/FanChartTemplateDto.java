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
public class FanChartTemplateDto {
    private Long id;
    private String name;
    private String userId;
    private String radius;
    private int precisions;
    private List<String> color;
    private String showLabel;
    private String showPercent;
    private int titleFont;
    private int labelFont;
    private String legendPos;
    private String textColor;
    private String isVisible;
    private Integer borderRadius;
    private String showRing;
    private String showRose;
    private String roseType;
}

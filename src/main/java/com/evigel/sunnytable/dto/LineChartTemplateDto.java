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
public class LineChartTemplateDto {
    private Long id;
    private String name;
    private String userId;
    private String radius;
    private List<Integer> point;
    private List<Integer> line;
    private List<String> color;
    private String showDigit;
    private Integer font;
    private String legendPos;
    private String textColor;
    private String isVisible;
    private String showSymbol;
    private String smooth;
    private String areaStyle;
    private String showArea;
    private String showEmphasis;
    private String showMinMarkPoint;
    private String showMaxMarkPoint;
    private Integer markPointSize;
    private Integer markPointStyle;
    private String showAverageMarkLine;
    private String showGradient;
    private String showXGradient;
    private String showYGradient;
    private String gradientColor;
    private String colorLightness;
    private String colorSaturation;
    private String colorHue;
    private String stack;
}

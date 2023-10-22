package com.evigel.sunnytable.dto;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScatterPlotTemplateDto {
    private Long id;
    private String name;
    private String userId;
    private List<Integer> point;
    private List<String> color;
    private String showLine;
    private String showDigit;
    private String increase;
    private int font;
    private String legendPos;
    private String textColor;
    private String isVisible;
    private String useRegression;
    private Integer indexRegression;
}

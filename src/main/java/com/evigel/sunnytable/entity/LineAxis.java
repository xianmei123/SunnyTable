package com.evigel.sunnytable.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.evigel.sunnytable.dto.LineChartTemplateDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author evigel
 * @since 2021-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("lineAxis")
@ApiModel(value="LineAxis对象", description="")
@AllArgsConstructor
public class LineAxis implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long mid;

    private String radius;

    @TableField("showDigit")
    private String showDigit;

    private Integer font;

    @TableField("textColor")
    private String textColor;

    @TableField("legendPos")
    private String legendPos;

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

    public static final String MID = "mid";

    public static final String SHOWDIGIT = "showDigit";

    public static final String FONT = "font";

    public static final String TEXTCOLOR = "textColor";

    public static final String LEGENDPOS = "legendPos";

    public LineAxis(LineChartTemplateDto template) {
        this.mid = template.getId();
        this.showDigit = template.getShowDigit();
        this.font = template.getFont();
        this.textColor = template.getTextColor();
        this.legendPos = template.getLegendPos();
        this.isVisible = template.getIsVisible();
        this.radius = template.getRadius();
        this.showSymbol = template.getShowSymbol();
        this.smooth = template.getSmooth();
        this.areaStyle = template.getAreaStyle();
        this.showArea = template.getShowArea();
        this.showEmphasis = template.getShowEmphasis();
        this.showMinMarkPoint = template.getShowMinMarkPoint();
        this.showMaxMarkPoint = template.getShowMaxMarkPoint();
        this.markPointSize = template.getMarkPointSize();
        this.markPointStyle = template.getMarkPointStyle();
        this.showAverageMarkLine = template.getShowAverageMarkLine();
        this.showGradient = template.getShowGradient();
        this.showXGradient = template.getShowXGradient();
        this.showYGradient = template.getShowYGradient();
        this.gradientColor = template.getGradientColor();
        this.colorLightness = template.getColorLightness();
        this.colorSaturation = template.getColorSaturation();
        this.colorHue = template.getColorHue();
        this.stack = template.getStack();

    }
}

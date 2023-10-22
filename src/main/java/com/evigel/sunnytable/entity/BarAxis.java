package com.evigel.sunnytable.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.evigel.sunnytable.dto.BarChartTemplateDto;
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
@TableName("barAxis")
@ApiModel(value="BarAxis对象", description="")
@AllArgsConstructor
public class BarAxis implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long mid;

    private Double width;

    private Double gap;

    @TableField("showDigit")
    private String showDigit;

    private String transpose;

    private Integer font;

    @TableField("textColor")
    private String textColor;

    @TableField("legendPos")
    private String legendPos;

    private String isVisible;

    private String showEmphasis;

    private String showMinMarkPoint;

    private String showMaxMarkPoint;

    private Integer markPointSize;

    private Integer markPointStyle;

    private String showAverageMarkLine;

    private String stack;

    public static final String MID = "mid";

    public static final String WIDTH = "width";

    public static final String GAP = "gap";

    public static final String SHOWDIGIT = "showDigit";

    public static final String TRANSPOSE = "transpose";

    public static final String FONT = "font";

    public static final String TEXTCOLOR = "textColor";

    public static final String LEGENDPOS = "legendPos";

    public BarAxis(BarChartTemplateDto template) {
        this.mid = template.getId();
        this.width = template.getWidth();
        this.gap = template.getGap();
        this.showDigit = template.getShowDigit();
        this.transpose = template.getTranspose();
        this.font = template.getFont();
        this.textColor = template.getTextColor();
        this.legendPos = template.getLegendPos();
        this.isVisible = template.getIsVisible();
        this.showEmphasis = template.getShowEmphasis();
        this.showMinMarkPoint = template.getShowMinMarkPoint();
        this.showMaxMarkPoint = template.getShowMaxMarkPoint();
        this.markPointSize = template.getMarkPointSize();
        this.markPointStyle = template.getMarkPointStyle();
        this.showAverageMarkLine = template.getShowAverageMarkLine();
        this.stack = template.getStack();
    }
}

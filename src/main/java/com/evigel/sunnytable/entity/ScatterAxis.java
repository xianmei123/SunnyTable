package com.evigel.sunnytable.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.evigel.sunnytable.dto.ScatterPlotTemplateDto;
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
@TableName("scatterAxis")
@ApiModel(value="ScatterAxis对象", description="")
@AllArgsConstructor
public class ScatterAxis implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long mid;

    @TableField("showLine")
    private String showLine;

    @TableField("showDigit")
    private String showDigit;

    private String increase;

    private Integer font;

    @TableField("textColor")
    private String textColor;

    @TableField("legendPos")
    private String legendPos;

    private String isVisible;

    private String useRegression;

    private Integer indexRegression;

    public static final String MID = "mid";

    public static final String SHOWLINE = "showLine";

    public static final String SHOWDIGIT = "showDigit";

    public static final String INCREASE = "increase";

    public static final String FONT = "font";

    public static final String TEXTCOLOR = "textColor";

    public static final String LEGENDPOS = "legendPos";

    public ScatterAxis(ScatterPlotTemplateDto template) {
        this.mid = template.getId();
        this.showLine = template.getShowLine();
        this.showDigit = template.getShowDigit();
        this.increase = template.getIncrease();
        this.font = template.getFont();
        this.legendPos = template.getLegendPos();
        this.textColor = template.getTextColor();
        this.isVisible = template.getIsVisible();
        this.useRegression = template.getUseRegression();
        this.indexRegression = template.getIndexRegression();
    }
}

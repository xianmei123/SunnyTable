package com.evigel.sunnytable.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.evigel.sunnytable.dto.FanChartTemplateDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
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
@TableName("pieAxis")
@ApiModel(value="PieAxis对象", description="")
@AllArgsConstructor
public class PieAxis implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long mid;

    private String radius;

    private Integer precisions;

    @TableField("showLabel")
    private String  showLabel;

    private String showPercent;

    private Integer titleFont;

    private Integer labelFont;

    @TableField("textColor")
    private String textColor;

    @TableField("legendPos")
    private String legendPos;

    private String isVisible;

    private Integer borderRadius;

    private String showRing;

    private String showRose;

    private String roseType;


    public static final String MID = "mid";

    public static final String RADIUS = "radius";

    public static final String PRECISON = "precison";

    public static final String SHOWLABEL = "showLabel";

    public static final String FONT = "font";

    public static final String TEXTCOLOR = "textColor";

    public static final String LEGENDPOS = "legendPos";

    public PieAxis(FanChartTemplateDto template) {
        this.mid = template.getId();
        this.radius = template.getRadius();
        this.precisions = template.getPrecisions();
        this.showLabel = template.getShowLabel();
        this.showPercent = template.getShowPercent();
        this.titleFont = template.getTitleFont();
        this.labelFont = template.getLabelFont();
        this.legendPos = template.getLegendPos();
        this.textColor = template.getTextColor();
        this.isVisible = template.getIsVisible();
        this.borderRadius = template.getBorderRadius();
        this.showRing = template.getShowRing();
        this.showRose = template.getShowRose();
        this.roseType = template.getRoseType();
    }
}

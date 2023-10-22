package com.evigel.sunnytable.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@ApiModel(value="Pdata对象", description="")
@AllArgsConstructor
@NoArgsConstructor
public class Pdata implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long pid;

    @ApiModelProperty(value = "画图所用的横坐标")
    private Long cid;

    @ApiModelProperty(value = "横坐标名称")
    private String xlabel;

    @ApiModelProperty(value = "纵坐标名称")
    private String ylabel;

    @ApiModelProperty(value = "横坐标起始")
    private Long xbegin;

    @ApiModelProperty(value = "纵坐标起始")
    private Long ybegin;

    @ApiModelProperty(value = "窗口宽度")
    private Double wwidth;

    @ApiModelProperty(value = "窗口高度")
    private Double wheight;


    public static final String TID = "pid";

    public static final String CID = "cid";

    public static final String XLABEL = "xlabel";

    public static final String YLABEL = "ylabel";

    public static final String XBEGIN = "xbegin";

    public static final String YBEGIN = "ybegin";

    public static final String WWIDTH = "wwidth";

    public static final String WHEIGHT = "wheight";

}

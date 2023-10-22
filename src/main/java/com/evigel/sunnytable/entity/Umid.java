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
@ApiModel(value="Umid对象", description="")
@NoArgsConstructor
@AllArgsConstructor
public class Umid implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户唯一id")
    private String uid;

    @ApiModelProperty(value = "模板名")
    private String mn;

    @ApiModelProperty(value = "模板类型")
    private Integer type;

    @ApiModelProperty(value = "模板唯一id")
    @TableId(type = IdType.ASSIGN_ID)
    private Long mid;

    private String isVisible;

    public static final String UID = "uid";

    public static final String MN = "mn";

    public static final String TYPE = "type";

    public static final String MID = "mid";

    public static final String SHOW = "show";

}

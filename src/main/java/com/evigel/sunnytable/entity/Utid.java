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
@ApiModel(value="Utid对象", description="")
@NoArgsConstructor
@AllArgsConstructor
public class Utid implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户唯一id")
    private String uid;

    @ApiModelProperty(value = "表名")
    private String tn;

    @ApiModelProperty(value = "表唯一id")
    @TableId(type = IdType.ASSIGN_ID)
    private Long tid;


    public static final String UID = "uid";

    public static final String TN = "tn";

    public static final String TID = "tid";

}

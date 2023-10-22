package com.evigel.sunnytable.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value="Ucid对象", description="")
@AllArgsConstructor
@NoArgsConstructor
public class Ucid implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户唯一id")
    private String uid;

    private Long tid;

    @ApiModelProperty(value = "列名")
    private String cn;

    @ApiModelProperty(value = "列唯一id")
    @TableId(type = IdType.ASSIGN_ID)
    @TableField
    private Long cid;

    @ApiModelProperty(value = "列类型")
    private Integer type;


    public static final String UID = "uid";

    public static final String TN = "tn";

    public static final String CN = "cn";

    public static final String CID = "cid";

    public static final String TYPE = "type";

}

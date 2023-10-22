package com.evigel.sunnytable.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

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
@ApiModel(value="Upid对象", description="")
@NoArgsConstructor
@AllArgsConstructor
public class Upid implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uid;

    private Long tid;

    private Long mid;

    @TableField("pName")
    private String pName;

    @TableId(type = IdType.ASSIGN_ID)
    private Long pid;


    public static final String UID = "uid";

    public static final String TID = "tid";

    public static final String MID = "mid";

    public static final String PNAME = "pName";

    public static final String PID = "pid";

}

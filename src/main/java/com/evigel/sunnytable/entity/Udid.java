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
@ApiModel(value="Udid对象", description="")
@AllArgsConstructor
@NoArgsConstructor
public class Udid implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uid;

    @TableField("dirName")
    private String dirName;

    @TableId(type = IdType.ASSIGN_ID)
    private Long did;

    private int type;

    public static final String UID = "uid";

    public static final String DIRNAME = "dirName";

    public static final String DID = "did";

    public static final String TYPE = "type";

}

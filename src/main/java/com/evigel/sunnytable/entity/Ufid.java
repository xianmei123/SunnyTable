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
@ApiModel(value="Ufid对象", description="")
@AllArgsConstructor
@NoArgsConstructor
public class Ufid implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uid;

    private Long did;

    @TableField("fname")
    private String fname;

    @TableId(type = IdType.ASSIGN_ID)
    private Long fid;

    private String isVisible;


    public static final String UID = "uid";

    public static final String DID = "did";

    public static final String FNAME = "fname";

    public static final String FID = "fid";


}

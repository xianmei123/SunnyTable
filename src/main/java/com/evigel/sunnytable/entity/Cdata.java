package com.evigel.sunnytable.entity;

import java.io.Serializable;
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
@ApiModel(value="Cdata对象", description="")
@AllArgsConstructor
public class Cdata implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "列唯一id")
    private Long cid;

    @ApiModelProperty(value = "序号")
    private Integer no;

    @ApiModelProperty(value = "值")
    private String val;


    public static final String CID = "cid";

    public static final String NO = "no";

    public static final String VAL = "val";

}

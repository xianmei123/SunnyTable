package com.evigel.sunnytable.entity;

import java.io.Serializable;
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
@ApiModel(value="Py对象", description="")
@AllArgsConstructor
@NoArgsConstructor
public class Py implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long pid;

    @ApiModelProperty(value = "序号")
    private Integer no;

    @ApiModelProperty(value = "画图所选纵坐标")
    private Long cid;


    public static final String PID = "pid";

    public static final String NO = "no";

    public static final String CID = "cid";

}

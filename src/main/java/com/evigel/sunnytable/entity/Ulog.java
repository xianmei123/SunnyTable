package com.evigel.sunnytable.entity;

import java.time.LocalDateTime;
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
@ApiModel(value="Ulog对象", description="")
@NoArgsConstructor
@AllArgsConstructor
public class Ulog implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uid;

    private LocalDateTime logIo;

    private Integer type;


    public static final String UID = "uid";

    public static final String LOGIO = "logIo";

    public static final String TYPE = "type";

}

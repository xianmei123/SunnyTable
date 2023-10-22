package com.evigel.sunnytable.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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
@ApiModel(value="Foid对象", description="")
@AllArgsConstructor
@NoArgsConstructor
public class Foid implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long fid;

    private Integer type;

    @TableField(fill= FieldFill.INSERT)
    private Timestamp createTime;

    @TableField(fill=FieldFill.INSERT_UPDATE)
    private Timestamp modifyTime;

    private Long oid;


    public static final String FID = "fid";

    public static final String TYPE = "type";

    public static final String SIZE = "size";

    public static final String CREATETIME = "createTime";

    public static final String MODIFYTIME = "modifyTime";

    public static final String OID = "oid";

}

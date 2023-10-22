package com.evigel.sunnytable.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ubid")
@ApiModel(value="Bill对象", description="")
@AllArgsConstructor
@NoArgsConstructor
public class Ubid implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uid;

    private String detail;

    private Date time;

    private String type;

    private String income;

    private Double cost;

    private Timestamp createTime;

    @TableId(type = IdType.ASSIGN_ID)
    @TableField
    private Long bid;
}

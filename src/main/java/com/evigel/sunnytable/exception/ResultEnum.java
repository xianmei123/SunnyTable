package com.evigel.sunnytable.exception;

public enum ResultEnum {
    UNKONW_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    INSERT_ERROR(1, "插入异常"),
    UPDATE_ERROR(2, "更新异常"),
    SELECT_ERROR(3, "查询异常"),
    DATA_ERROR(11, "传入数据错误"),
    ;

    private Integer code;

    private String type;

    ResultEnum(Integer code, String type) {
        this.code = code;
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public String getType() {
        return type;
    }
}

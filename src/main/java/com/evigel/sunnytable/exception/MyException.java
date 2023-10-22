package com.evigel.sunnytable.exception;

public class MyException extends RuntimeException {
    private Integer code;

    private String type;

    public MyException(ResultEnum resultEnum, String message) {
        super(message);
        this.code = resultEnum.getCode();
        this.type = resultEnum.getType();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

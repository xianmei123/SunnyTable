package com.evigel.sunnytable.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Map<String, Object> handleMyException(MyException me) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", me.getCode());
        result.put("type", me.getType());
        result.put("message", me.getMessage());
        return result;
    }

    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public Map<String, Object> handlerSqlException(SQLException se) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 21);
        result.put("type", "数据库异常");
        result.put("message", se.getMessage());
        return result;
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseBody
    public Map<String, Object> handlerDataAccessException(DataAccessException se) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 31);
        result.put("type", "数据库异常");
        result.put("message", se.getMessage());
        return result;
    }
}

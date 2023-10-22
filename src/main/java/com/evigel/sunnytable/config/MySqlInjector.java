package com.evigel.sunnytable.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.AlwaysUpdateSomeColumnById;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;

import java.util.List;

public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        //防止父类方法不可用
        List<AbstractMethod> methods = super.getMethodList(mapperClass);
        methods.add(new InsertBatchSomeColumn());
        methods.add(new AlwaysUpdateSomeColumnById());
        methods.add(new UpdateBatchInjector());
        return methods;
    }
}

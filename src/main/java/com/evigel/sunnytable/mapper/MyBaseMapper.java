package com.evigel.sunnytable.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;

public interface MyBaseMapper<T> extends BaseMapper<T> {

    /**
     * 批量插入 仅适用于mysql
     *
     * @param entityList 实体列表
     * @return 影响行数
     */
    Integer insertBatchSomeColumn(Collection<T> entityList);

    Integer updateBatch(Collection<T> entityList);
}


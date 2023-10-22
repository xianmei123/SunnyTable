package com.evigel.sunnytable.mapper;

import com.evigel.sunnytable.entity.Ulog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author evigel
 * @since 2021-04-28
 */
@Component
public interface UlogMapper extends MyBaseMapper<Ulog> {

    List<String> getUsers();
}

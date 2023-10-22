package com.evigel.sunnytable.mapper;

import com.evigel.sunnytable.entity.Mcolor;
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
public interface McolorMapper extends MyBaseMapper<Mcolor> {
    List<String> getColors(long mid);
}

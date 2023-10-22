package com.evigel.sunnytable.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.evigel.sunnytable.entity.Mpoint;
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
public interface MpointMapper extends MyBaseMapper<Mpoint> {
    List<Integer> getPoints(Long mid);
}

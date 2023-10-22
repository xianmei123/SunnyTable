package com.evigel.sunnytable.mapper;

import com.evigel.sunnytable.entity.Umid;
import com.evigel.sunnytable.entity.Upid;
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
public interface UpidMapper extends MyBaseMapper<Upid> {
    List<Upid> getChartByName(String uid, String name);
    List<Integer> getType(Long pid);
}

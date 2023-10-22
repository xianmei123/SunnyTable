package com.evigel.sunnytable.mapper;

import com.evigel.sunnytable.entity.Ucid;
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
public interface UcidMapper extends MyBaseMapper<Ucid> {

    List<String> getColumn(Long cid);
}

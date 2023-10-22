package com.evigel.sunnytable.mapper;

import com.evigel.sunnytable.dto.DataSetDto;
import com.evigel.sunnytable.entity.Utid;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
public interface UtidMapper extends MyBaseMapper<Utid> {

    List<DataSetDto> getDataSet(long fid);
}

package com.evigel.sunnytable.mapper;

import com.evigel.sunnytable.dto.BarChartTemplateDto;
import com.evigel.sunnytable.dto.ScatterPlotTemplateDto;
import com.evigel.sunnytable.entity.ScatterAxis;
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
public interface ScatterAxisMapper extends MyBaseMapper<ScatterAxis> {
    List<ScatterPlotTemplateDto> getTemplate(long fid);
}

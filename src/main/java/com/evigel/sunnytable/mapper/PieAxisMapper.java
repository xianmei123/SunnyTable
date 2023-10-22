package com.evigel.sunnytable.mapper;

import com.evigel.sunnytable.dto.BarChartTemplateDto;
import com.evigel.sunnytable.dto.FanChartTemplateDto;
import com.evigel.sunnytable.entity.PieAxis;
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
public interface PieAxisMapper extends MyBaseMapper<PieAxis> {
    List<FanChartTemplateDto> getTemplate(long fid);
}

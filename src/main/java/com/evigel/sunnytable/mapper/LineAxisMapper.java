package com.evigel.sunnytable.mapper;

import com.evigel.sunnytable.dto.LineChartTemplateDto;
import com.evigel.sunnytable.entity.LineAxis;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import javax.sound.sampled.Line;
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
public interface LineAxisMapper extends MyBaseMapper<LineAxis> {
    List<LineChartTemplateDto> getTemplate(long fid);
}

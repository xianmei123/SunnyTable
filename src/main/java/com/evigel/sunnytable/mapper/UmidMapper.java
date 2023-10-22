package com.evigel.sunnytable.mapper;

import com.evigel.sunnytable.dto.TemplateInfoDto;
import com.evigel.sunnytable.entity.Umid;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.evigel.sunnytable.service.impl.TemplateServiceImpl;
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
public interface UmidMapper extends MyBaseMapper<Umid> {
    List<TemplateInfoDto> getTemplates(String uid);
    TemplateInfoDto getTemplateByFid(Long fid);
    List<Umid> getTemplateByName(String uid, String fname);
}

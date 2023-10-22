package com.evigel.sunnytable.service.impl;


import com.evigel.sunnytable.service.IProcessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author evigel
 * @since 2021-04-28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProcessServiceImpl implements IProcessService {

}

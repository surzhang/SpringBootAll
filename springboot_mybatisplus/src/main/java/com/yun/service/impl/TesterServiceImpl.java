package com.yun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yun.dao.TesterDao;
import com.yun.entity.Tester;
import com.yun.service.TesterService;
import org.springframework.stereotype.Service;

/**
 * (Tester)表服务实现类
 *
 * @author makejava
 * @since 2021-11-25 09:48:49
 */
@Service("testerService")
public class TesterServiceImpl extends ServiceImpl<TesterDao, Tester> implements TesterService {

}


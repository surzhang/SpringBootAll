package com.yun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yun.dao.QuestionDao;
import com.yun.entity.Question;
import com.yun.service.QuestionService;
import org.springframework.stereotype.Service;

/**
 * 问题表(Question)表服务实现类
 * @author makejava
 * @since 2021-11-24 14:59:22
 */
@Service("questionService")
public class QuestionServiceImpl extends ServiceImpl<QuestionDao, Question> implements QuestionService {

}


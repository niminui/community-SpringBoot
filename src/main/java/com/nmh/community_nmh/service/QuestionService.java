package com.nmh.community_nmh.service;

import com.nmh.community_nmh.mapper.QuestionMapper;
import com.nmh.community_nmh.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author niminui
 * @date 2020/3/7 11:27
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    public List<Question> list() {
        return questionMapper.findQuestionWithUser();
    }

    public List<Question> listOfCreator(Integer userId) {
        return questionMapper.findQuestionWithUserById(userId);
    }

    public Question getById(Integer QId) {
        return questionMapper.getOneQuestionWithUserByQId(QId);
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null) {
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        } else {
            //更新
            question.setGmtModified(question.getGmtCreate());
            questionMapper.updateByPrimaryKeySelective(question);
        }
    }
}

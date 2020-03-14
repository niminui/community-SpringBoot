package com.nmh.community_nmh.service;

import com.nmh.community_nmh.exception.CustomizeErrorCode;
import com.nmh.community_nmh.exception.CustomizeException;
import com.nmh.community_nmh.mapper.QuestionExtMapper;
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

    @Autowired
    private QuestionExtMapper questionExtMapper;

    public List<Question> list() {
        return questionExtMapper.findQuestionWithUser();
    }

    public List<Question> listOfCreator(Integer userId) {
        return questionExtMapper.findQuestionWithUserById(userId);
    }

    public Question getById(Integer QId) {
        Question question = questionExtMapper.getOneQuestionWithUserByQId(QId);
        if(question ==null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        return question;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null) {
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            questionMapper.insertSelective(question);
        } else {
            //更新
            question.setGmtModified(question.getGmtCreate());
            int updated = questionMapper.updateByPrimaryKeySelective(question);
            if(updated != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Integer id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }
}

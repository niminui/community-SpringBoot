package com.nmh.community_nmh.service;

import com.nmh.community_nmh.exception.CustomizeErrorCode;
import com.nmh.community_nmh.exception.CustomizeException;
import com.nmh.community_nmh.mapper.QuestionExtMapper;
import com.nmh.community_nmh.mapper.QuestionMapper;
import com.nmh.community_nmh.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Question> list(String search) {
        if(!StringUtils.isEmpty(search)) {
            String[] tags = search.split(" ");
            search = String.join("|", tags);
            return questionExtMapper.findQuestionWithUserBySearch(search);
        }
        return questionExtMapper.findQuestionWithUser();
    }

    public List<Question> listOfCreator(Long userId) {
        return questionExtMapper.findQuestionWithUserById(userId);
    }

    public Question getById(Long QId) {
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
            question.setGmtModified(System.currentTimeMillis());
            int updated = questionMapper.updateByPrimaryKeySelective(question);
            if(updated != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }

    public List<Question> selectRelated(Question question) {
        if(StringUtils.isEmpty(question.getTag())) {
            return new ArrayList<>();
        }
        String tempTag = question.getTag().replaceAll(",", "|");
        question.setTag(tempTag);
        return questionExtMapper.selectRelated(question);
    }
}

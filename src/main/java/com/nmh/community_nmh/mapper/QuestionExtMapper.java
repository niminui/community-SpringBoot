package com.nmh.community_nmh.mapper;

import com.nmh.community_nmh.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question question);

    /**
     * 查询所有问题，同时查询每一个问题的发表者user
     */
    List<Question> findQuestionWithUser();

    /**
     * 根据用户id查询该id发布的的所有问题
     */
    List<Question> findQuestionWithUserById(Integer userId);

    /**
     * 根据问题id查询单个问题并查询出所属的用户
     */
    Question getOneQuestionWithUserByQId(Integer QId);
}
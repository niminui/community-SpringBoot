package com.nmh.community_nmh.service;

import com.nmh.community_nmh.dto.PaginationDTO;
import com.nmh.community_nmh.dto.QuestionDTO;
import com.nmh.community_nmh.mapper.QuestionMapper;
import com.nmh.community_nmh.mapper.UserMapper;
import com.nmh.community_nmh.model.Question;
import com.nmh.community_nmh.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return questionMapper.list();
    }
}

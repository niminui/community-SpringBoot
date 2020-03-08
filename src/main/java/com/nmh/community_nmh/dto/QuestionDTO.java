package com.nmh.community_nmh.dto;

import com.nmh.community_nmh.model.Question;
import com.nmh.community_nmh.model.User;
import lombok.Data;

/**
 * @author niminui
 * @date 2020/3/7 11:24
 */
@Data
public class QuestionDTO extends Question {

    private User user;

}

package com.nmh.community_nmh.controller;

import com.nmh.community_nmh.dto.CommentDTO;
import com.nmh.community_nmh.enums.CommentTypeEnum;
import com.nmh.community_nmh.model.Question;
import com.nmh.community_nmh.service.CommentService;
import com.nmh.community_nmh.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author niminui
 * @date 2020/3/10 11:08
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Long id, Model model) {
        Question question = questionService.getById(id);
        Question tempQuestion = new Question();
        BeanUtils.copyProperties(question,tempQuestion);
        List<Question> relatedQuestion = questionService.selectRelated(tempQuestion);
        List<CommentDTO> comments = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);

        //累加阅读数
        questionService.incView(id);
        model.addAttribute("question",question);
        model.addAttribute("comments",comments);
        model.addAttribute("relatedQuestion",relatedQuestion);
        return "question";
    }

}

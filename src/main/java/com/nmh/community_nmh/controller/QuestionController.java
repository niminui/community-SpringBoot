package com.nmh.community_nmh.controller;

import com.nmh.community_nmh.dto.CommentDTO;
import com.nmh.community_nmh.model.Question;
import com.nmh.community_nmh.service.CommentService;
import com.nmh.community_nmh.service.QuestionService;
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
    public String question(@PathVariable(name = "id")Integer id, Model model) {
        Question question = questionService.getById(id);

        List<CommentDTO> comments = commentService.listByQuestionId(id);

        //累加阅读数
        questionService.incView(id);
        model.addAttribute("question",question);
        model.addAttribute("comments",comments);
        return "question";
    }

}

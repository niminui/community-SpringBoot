package com.nmh.community_nmh.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nmh.community_nmh.mapper.UserMapper;
import com.nmh.community_nmh.model.Question;
import com.nmh.community_nmh.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author niminui
 * @date 2020/3/4 11:59
 */
@Controller
public class IndexController {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        PageHelper.startPage(page, size);
        List<Question> questions = questionService.list();
        PageInfo<Question> pageInfo = new PageInfo<>(questions,5);
        model.addAttribute("questions", questions);
        model.addAttribute("pageInfo",pageInfo);

        return "index";
    }

}

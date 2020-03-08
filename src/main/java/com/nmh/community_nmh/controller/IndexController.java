package com.nmh.community_nmh.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nmh.community_nmh.dto.PaginationDTO;
import com.nmh.community_nmh.dto.QuestionDTO;
import com.nmh.community_nmh.mapper.UserMapper;
import com.nmh.community_nmh.model.Question;
import com.nmh.community_nmh.model.User;
import com.nmh.community_nmh.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author niminui
 * @date 2020/3/4 11:59
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer pn,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }


        PageHelper.startPage(pn, size);
        //List<QuestionDTO> questions = questionService.list();
        List<Question> questions = questionService.list();
        PageInfo<Question> pageInfo = new PageInfo<>(questions,5);
        model.addAttribute("questions", questions);
        model.addAttribute("pageInfo",pageInfo);

        return "index";
    }

}

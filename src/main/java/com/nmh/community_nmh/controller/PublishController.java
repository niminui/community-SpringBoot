package com.nmh.community_nmh.controller;

import com.nmh.community_nmh.mapper.QuestionMapper;
import com.nmh.community_nmh.mapper.UserMapper;
import com.nmh.community_nmh.model.Question;
import com.nmh.community_nmh.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author niminui
 * @date 2020/3/6 11:03
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title")String title,
                            @RequestParam("description")String description,
                            @RequestParam("tag")String tag,
                            HttpServletRequest request,
                            Model model) {
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if(title == null || title.equals("")) {
            model.addAttribute("error","标题不能为空！");
            return "publish";
        }
        if(description == null || description.equals("")) {
            model.addAttribute("error","问题补充不能为空！");
            return "publish";
        }
        if(tag == null || tag.equals("")) {
            model.addAttribute("error","标签不能为空！");
            return "publish";
        }

        User user = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if(user != null) {
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }

        if(user == null) {
            model.addAttribute("error","用户未登录！");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());

        questionMapper.create(question);
        return "redirect:/";
    }
}

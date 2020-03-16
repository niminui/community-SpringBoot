package com.nmh.community_nmh.controller;

import com.nmh.community_nmh.cache.TagCache;
import com.nmh.community_nmh.mapper.QuestionMapper;
import com.nmh.community_nmh.model.Question;
import com.nmh.community_nmh.model.User;
import com.nmh.community_nmh.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author niminui
 * @date 2020/3/6 11:03
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable("id") Long id,
                       Model model) {
        Question question = questionService.getById(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());

        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(Model model) {
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title", required = false) String title,
                            @RequestParam(value = "description", required = false) String description,
                            @RequestParam(value = "tag", required = false) String tag,
                            @RequestParam(value = "id", required = false) Long id,
                            HttpServletRequest request,
                            Model model) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        model.addAttribute("tags", TagCache.get());

        if (title == null || title.equals("") || title.replaceAll(" ", "").equals("")) {
            model.addAttribute("error", "标题不能为空！");
            return "publish";
        }
        if (description == null || description.equals("") || description.replaceAll(" ", "").equals("")) {
            model.addAttribute("error", "问题补充不能为空！");
            return "publish";
        }
        if (tag == null || tag.equals("") || tag.replaceAll(" ", "").equals("")) {
            model.addAttribute("error", "标签不能为空！");
            return "publish";
        }
        tag = tag.replaceAll("，", ",");
        String invalid = TagCache.filterInvalid(tag);
        if (!StringUtils.isEmpty(invalid)) {
            model.addAttribute("error", "输入非法标签" + invalid);
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录！");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setId(id);

        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}

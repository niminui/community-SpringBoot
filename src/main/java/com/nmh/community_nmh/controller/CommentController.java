package com.nmh.community_nmh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author niminui
 * @date 2020/3/13 23:53
 */
@Controller
public class CommentController {

    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object postComment() {
        return null;
    }

}

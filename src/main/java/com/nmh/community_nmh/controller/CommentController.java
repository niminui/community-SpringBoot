package com.nmh.community_nmh.controller;

import com.nmh.community_nmh.dto.CommentDTO;
import com.nmh.community_nmh.dto.ResultDTO;
import com.nmh.community_nmh.exception.CustomizeErrorCode;
import com.nmh.community_nmh.mapper.CommentMapper;
import com.nmh.community_nmh.model.Comment;
import com.nmh.community_nmh.model.User;
import com.nmh.community_nmh.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author niminui
 * @date 2020/3/13 23:53
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object postComment(@RequestBody CommentDTO commentDTO,
                              HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if(user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO,comment);
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(1);
        comment.setLikeCount(0L);
        commentService.insert(comment);
        return ResultDTO.successOf();
    }

}

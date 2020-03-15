package com.nmh.community_nmh.controller;

import com.nmh.community_nmh.dto.CommentCreateDTO;
import com.nmh.community_nmh.dto.ResultDTO;
import com.nmh.community_nmh.exception.CustomizeErrorCode;
import com.nmh.community_nmh.model.Comment;
import com.nmh.community_nmh.model.User;
import com.nmh.community_nmh.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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
    public Object postComment(@RequestBody CommentCreateDTO commentCreateDTO,
                              HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if(user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        if(commentCreateDTO == null ||
                StringUtils.isEmpty(commentCreateDTO.getContent().replaceAll(" ",""))) {
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }

        Comment comment = new Comment();
        BeanUtils.copyProperties(commentCreateDTO,comment);
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment);
        return ResultDTO.successOf();
    }

}

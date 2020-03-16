package com.nmh.community_nmh.mapper;

import com.nmh.community_nmh.model.Comment;
import com.nmh.community_nmh.model.CommentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}
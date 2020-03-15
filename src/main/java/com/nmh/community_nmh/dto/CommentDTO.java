package com.nmh.community_nmh.dto;

import com.nmh.community_nmh.model.User;
import lombok.Data;

/**
 * @author niminui
 * @date 2020/3/15 12:17
 */
@Data
public class CommentDTO {
    private Integer id;
    private Integer parentId;
    private Integer type;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;

    private User user;
}

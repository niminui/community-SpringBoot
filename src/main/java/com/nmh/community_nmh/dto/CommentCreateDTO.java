package com.nmh.community_nmh.dto;

import lombok.Data;

/**
 * @author niminui
 * @date 2020/3/14 11:17
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}

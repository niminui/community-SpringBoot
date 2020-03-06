package com.nmh.community_nmh.model;

import lombok.Data;

/**
 * @author niminui
 * @date 2020/3/6 12:27
 */
@Data
public class Question {
    private Integer id;
    private Integer creator;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;

}

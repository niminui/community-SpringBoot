package com.nmh.community_nmh.model;

import lombok.Data;

/**
 * @author niminui
 * @date 2020/3/5 22:32
 */
@Data
public class User {

    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;

}

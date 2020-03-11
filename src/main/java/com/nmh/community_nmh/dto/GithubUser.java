package com.nmh.community_nmh.dto;

import lombok.Data;

/**
 * @author niminui
 * @date 2020/3/5 19:35
 */
@Data
public class GithubUser {

    private String name;
    private Long id;
    private String bio;
    private String avatar_url;

}

package com.nmh.community_nmh.dto;

import lombok.Data;

/**
 * @author niminui
 * @date 2020/3/4 23:14
 * DTO(data transfer object)：数据传输模型
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}

package com.nmh.community_nmh.dto;

import lombok.Data;

import java.net.URL;

/**
 * @author niminui
 * @date 2020/3/18 12:15
 */
@Data
public class FileDTO {
    private int success;
    private String message;
    private URL url;
}

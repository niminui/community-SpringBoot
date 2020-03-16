package com.nmh.community_nmh.dto;

import lombok.Data;

import java.util.List;

/**
 * @author niminui
 * @date 2020/3/16 22:47
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}

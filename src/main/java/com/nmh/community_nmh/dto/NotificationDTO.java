package com.nmh.community_nmh.dto;

import com.nmh.community_nmh.model.User;
import lombok.Data;

/**
 * @author niminui
 * @date 2020/3/17 12:04
 */
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outerid;
    private String typeName;
    private Integer type;
}

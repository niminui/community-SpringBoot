package com.nmh.community_nmh.service;

import com.nmh.community_nmh.dto.NotificationDTO;
import com.nmh.community_nmh.enums.NotificationStatusEnum;
import com.nmh.community_nmh.enums.NotificationTypeEnum;
import com.nmh.community_nmh.exception.CustomizeErrorCode;
import com.nmh.community_nmh.exception.CustomizeException;
import com.nmh.community_nmh.mapper.NotificationMapper;
import com.nmh.community_nmh.model.Notification;
import com.nmh.community_nmh.model.NotificationExample;
import com.nmh.community_nmh.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author niminui
 * @date 2020/3/17 12:10
 */
@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    public List<NotificationDTO> list(Long userId) {
        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(userId);
        example.setOrderByClause("gmt_create desc");
        List<Notification> notifications = notificationMapper.selectByExample(example);

        if(notifications.size() == 0) {
            return new ArrayList<NotificationDTO>();
        }

        List<NotificationDTO> notificationDTOS = new ArrayList<>();

        for(Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification,notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOS.add(notificationDTO);
        }

        return notificationDTOS;
    }

    public Long unreadCount(Long userId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId)
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(notificationExample);
    }

    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if(notification == null) {
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if(!notification.getReceiver().equals(user.getId())) {
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);

        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification,notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;
    }
}

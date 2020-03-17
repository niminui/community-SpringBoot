package com.nmh.community_nmh.controller;

import com.nmh.community_nmh.dto.NotificationDTO;
import com.nmh.community_nmh.enums.NotificationTypeEnum;
import com.nmh.community_nmh.model.User;
import com.nmh.community_nmh.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * @author niminui
 * @date 2020/3/17 15:03
 */
@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(HttpServletRequest request, @PathVariable(name = "id") Long id) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        NotificationDTO notificationDTO = notificationService.read(id, user);

        if(NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType()
                || NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDTO.getType()) {
            return "redirect:/question/"+notificationDTO.getOuterid();
        } else {
            return "redirect:/";
        }
    }

}

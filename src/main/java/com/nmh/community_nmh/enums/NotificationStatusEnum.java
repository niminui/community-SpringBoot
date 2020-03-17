package com.nmh.community_nmh.enums;

/**
 * @author niminui
 * @date 2020/3/17 11:35
 */
public enum  NotificationStatusEnum {
    UNREAD(0),READ(1);
    private int status;

    NotificationStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }


}

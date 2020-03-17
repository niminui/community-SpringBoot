package com.nmh.community_nmh.enums;

/**
 * @author niminui
 * @date 2020/3/17 11:23
 */
public enum NotificationTypeEnum {
    REPLY_QUESTION(1,"回复了你发布的问题"),
    REPLY_COMMENT(2,"回复了你的评论")
    ;
    private int type;
    private String name;

    NotificationTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static String nameOfType(int type) {
        for (NotificationTypeEnum notificationTypeEnum : NotificationTypeEnum.values()) {
            if(notificationTypeEnum.getType() == type) {
                return notificationTypeEnum.getName();
            }
        }
        return "";
    }
}

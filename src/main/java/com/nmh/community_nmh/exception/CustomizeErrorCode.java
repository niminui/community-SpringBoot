package com.nmh.community_nmh.exception;

/**
 * @author niminui
 * @date 2020/3/13 14:16
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("你找的问题不见了，换一个试试看？");

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

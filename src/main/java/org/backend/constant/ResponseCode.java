package org.backend.constant;

import lombok.Getter;

@Getter
public enum ResponseCode {

    SUCCESS("000", "Success"),
    INTERNAL_SERVER_ERROR("001", "Internal server error"),
    ACCOUNT_ALREADY_EXIST("002", "Account already exist"),
    ACCESS_NOTFOUND("003", "Menu not found"),
    USER_ACCESS_NOTFOUND("004", "User access not found"),
    USERNAME_NOTFOUND("005", "Username not found"),
    USERNAME_ALREADY_DEACTIVE("006", "Username already deactive"),
    REQ_FORMAT_ERROR("101", "Format error");

    private String code;
    private String description;

    ResponseCode(String code, String description) {
        this.code = code;
        this.description = description;
    }


}
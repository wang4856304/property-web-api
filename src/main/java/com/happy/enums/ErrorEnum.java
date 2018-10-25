package com.happy.enums;

public enum ErrorEnum {
    USER_NAME_PASSWORD_ERROR("1001", "用户名或密码错误"),
    USER_FREEZEN_ERROR("1002", "账号已冻结,请联系管理员"),
    PARAM_VALID_ERROR("1003", "参数校验错误"),

    TOKEN_EMPTY_ERROR("1004", "token为空"),
    TOKEN_INVALID_ERROR("1005", "token无效"),
    TOKEN_NOT_FOUND_ERROR("1006", "不存在token");

    private ErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

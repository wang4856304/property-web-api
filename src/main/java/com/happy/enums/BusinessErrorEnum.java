package com.happy.enums;

/**
 * @author wangjun
 * @Title: BusinessErrorEnum
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/12 11:13
 */
public enum BusinessErrorEnum {

    NO_COMMUNITY_ERROR("3005", "小区不存在"),
    ROOM_OWNER_ERROR("3006", "房间与房主不匹配"),
    PARK_SPACE_CODE_REPEAT_ERROR("3007", "车位号已存在"),
    OWNER_REPEAT_ERROR("3009", "业主已存在"),
    OWNER_NOT_FOUND_ERROR("3008", "业主不存在"),
    COST_CONFIG_NOT_FOUND_ERROR("3010", "请先配置收费方式");



    private BusinessErrorEnum(String code, String msg) {
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

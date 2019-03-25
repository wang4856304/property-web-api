package com.happy.utils;

import java.util.UUID;

/**
 * @author wangjun
 * @Title: TokenUtil
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/17 16:45
 */
public class TokenUtil {

    public static String getUUID32() {
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        return uuid;
    }
}

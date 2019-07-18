package com.happy.interceptor;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.resource.jdbc.spi.StatementInspector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author jun.wang
 * @title: AESInterceptor
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/7/16 15:48
 */
public class AESInterceptor implements StatementInspector {

    private final static String SELECT = "select";

    private static Logger logger = LoggerFactory.getLogger(AESInterceptor.class);

    private static final String USER_INFO_MOBILE = "mobile";

    private static final String USER_INFO_EMAIL = "email";

    private static Map<String, List<String>> tableColMap = new ConcurrentHashMap<>();

    static {
        tableColMap.put("s_user_info", Arrays.asList("mobile", "email"));
    }


    @Override
    public String inspect(String sql) {
        if (sql == null || sql.length() == 0) {
            throw new RuntimeException("sql is empty");
        }
        logger.info("old sql={}", sql);
        if (sql.startsWith(SELECT) || sql.startsWith(SELECT.toUpperCase())) {
            if (tableColMap.containsKey("s_user_info")) {
                //sql = sql.replace(USER_INFO_MOBILE, decodeMobile());
                //sql = sql.replace(USER_INFO_EMAIL, decodeEmail());
                logger.info("new sql={}", sql);
            }
        }
        return sql;
    }

    /*String decodeMobile() {
        return "AES_DECRYPT(from_base64(encrypt_mobile),'" + AESUtil.key + "')";
    }

    String decodeEmail() {
        return "AES_DECRYPT(from_base64(encrypt_email),'" + AESUtil.key + "')";
    }*/
}

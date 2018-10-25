package com.happy.service;

import com.happy.entity.Response;

/**
 * }
 *
 * @author Administrator
 * @Description:
 * @date 2018/8/20 13:26
 */
public interface SmsSendService {
    Response sendVerifyCode(String mobile);
}

package com.happy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.happy.entity.Response;
import com.happy.service.BaseService;
import com.happy.service.SmsSendService;
import com.happy.utils.HttpClientUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * }
 *
 * @author Administrator
 * @Description:
 * @date 2018/8/20 13:27
 */
@Service
public class SmsSendServiceImpl extends BaseService implements SmsSendService {

    private static Log log = LogFactory.getLog(SmsSendServiceImpl.class);

    @Value("${smsSendUrl}")
    private String smsSendUrl;

    @Override
    public Response sendVerifyCode(String mobile) {
        String url = smsSendUrl + "?" + mobile;
        String result = HttpClientUtil.httpGetRequest(url);
        Response response = JSONObject.parseObject(result, Response.class);
        return response;
    }
}

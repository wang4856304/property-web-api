package com.happy.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.happy.entity.Response;
import com.happy.enums.ErrorEnum;
import com.happy.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @author wangjun
 * @Title: CheckInterceptor
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/10 16:24
 */

@Component
public class CheckInterceptor implements HandlerInterceptor {

    private static final String TOKEN = "token";
    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String methodName = httpServletRequest.getMethod();
        if (!"get".equalsIgnoreCase(methodName)&&!"post".equalsIgnoreCase(methodName)) {
            return true;
        }
        String token = "";
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        boolean hasToken = false;
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            if (TOKEN.equals(headerName)) {
                token = httpServletRequest.getHeader(TOKEN);
                hasToken = true;
            }
        }
        if (hasToken) {
            if (StringUtils.isEmpty(token)) {
                Response response = buildErrorResponse(ErrorEnum.TOKEN_EMPTY_ERROR.getCode(), ErrorEnum.TOKEN_EMPTY_ERROR.getMsg());
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.setContentType("application/json; charset=utf-8");
                httpServletResponse.getWriter().println(JSONObject.toJSONString(response));
                return false;
            }
            else {
                String managerId = redisService.get(token);
                if (StringUtils.isEmpty(managerId)) {
                    Response response = buildErrorResponse(ErrorEnum.TOKEN_INVALID_ERROR.getCode(), ErrorEnum.TOKEN_INVALID_ERROR.getMsg());
                    httpServletResponse.setCharacterEncoding("UTF-8");
                    httpServletResponse.setContentType("application/json; charset=utf-8");
                    httpServletResponse.getWriter().println(JSONObject.toJSONString(response));
                    return false;
                }
            }
        }
        else {
            Response response = buildErrorResponse(ErrorEnum.TOKEN_NOT_FOUND_ERROR.getCode(), ErrorEnum.TOKEN_NOT_FOUND_ERROR.getMsg());
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json; charset=utf-8");
            httpServletResponse.getWriter().println(JSONObject.toJSONString(response));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public Response buildErrorResponse(String code, String msg) {
        Response response = new Response();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }
}

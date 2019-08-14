package com.happy.security;

import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author jun.wang
 * @title: RoleBasedVoter
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/8/8 14:55
 */
public class RoleBasedVoter implements AccessDecisionVoter<Object> {

    private static Map<String, List<String>> urlMap = new HashMap<>();
    static {
        urlMap.put("-1", Arrays.asList("/test/hello", "/test/hello/*"));
        urlMap.put("0", Arrays.asList("/test/xx", "/test/yy"));
    }

    private static final String PRIVATE_RESOURCE_TYPE = "1";//私有资源
    private static final String OPEN_RESOURCE_TYPE = "0";//开放资源
    private static final String ACCESS_TOKEN = "access_token";

    private UserInfoTokenServices userInfoTokenServices;


    public RoleBasedVoter(UserInfoTokenServices userInfoTokenServices) {
        this.userInfoTokenServices = userInfoTokenServices;
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object o, Collection<ConfigAttribute> collection) {
        Iterator<ConfigAttribute> iterator = collection.iterator();
        FilterInvocation fi = (FilterInvocation) o;
        HttpServletRequest request = fi.getHttpRequest();
        if (iterator.hasNext()) {
            ConfigAttribute attribute = iterator.next();
            String attr = attribute.getAttribute();
            if (!checkMethod(request,"post")) {
                throw new RuntimeException("method not allowed");
            }
            if (PRIVATE_RESOURCE_TYPE.equals(attr)) {
                String token = getAccessToken(request);
                if (StringUtils.isEmpty(token)) {
                    throw new AccessDeniedException("Access is denied");
                }
                userInfoTokenServices.loadAuthentication(token);
                return 1;
            }
            else if (OPEN_RESOURCE_TYPE.equals(attr)) {
                return 1;
            }
        }

        /*String url = request.getRequestURI();
        List<String> touristUrls = urlMap.get("-1");
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        antPathMatcher.setCaseSensitive(false);
        boolean result = touristUrls.stream().anyMatch(str->antPathMatcher.match(str, url));//匹配集合的url，支持*,?,+通配符
        if (result) {
            return 1;
        }*/
        return -1;
    }

    private String getAccessToken(HttpServletRequest request) {
        String token = request.getParameter(ACCESS_TOKEN);
        if (StringUtils.isEmpty(token)) {
            token = request.getHeader(ACCESS_TOKEN);
        }
        return token;
    }

    private boolean checkMethod(HttpServletRequest request, String method) {
        return request.getMethod().equalsIgnoreCase(method);
    }

    public static void main(String args[]) {
        List<String> touristUrls = urlMap.get("-1");
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        antPathMatcher.setCaseSensitive(false);
        boolean result = touristUrls.stream().anyMatch(str->antPathMatcher.match(str, "/test/hello/uuu"));
        if (result) {
            System.out.println(1);
        }

    }
}

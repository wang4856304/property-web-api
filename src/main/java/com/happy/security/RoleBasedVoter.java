package com.happy.security;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.util.*;
import java.util.function.Predicate;

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
        //FilterInvocationSecurityMetadataSource
        FilterInvocation fi = (FilterInvocation) o;
        String url = fi.getHttpRequest().getRequestURI();
        List<String> touristUrls = urlMap.get("-1");
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        antPathMatcher.setCaseSensitive(false);
        boolean result = touristUrls.stream().anyMatch(str->antPathMatcher.match(str, url));//匹配集合的url，支持*,?,+通配符
        if (result) {
            return 1;
        }
        return 0;
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

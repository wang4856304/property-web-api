package com.happy.security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author jun.wang
 * @title: FilterSecurityMetadataSource
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/8/9 9:43
 */

//@Component
public class FilterSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;


    /*
     * 在构造方法里初始化url权限数据，我们只要保证在 getAttributes()之前初始好数据就可以了
     */
    public FilterSecurityMetadataSource() {
        Map<RequestMatcher, Collection<ConfigAttribute>> map = new HashMap<>();
        AntPathRequestMatcher matcher = new AntPathRequestMatcher("/test/hello");
        //SecurityConfig config = new SecurityConfig("ROLE_ANONYMOUS");
        List<ConfigAttribute> configs = SecurityConfig.createList("1");
        //configs.add(config);
        map.put(matcher,configs);

        AntPathRequestMatcher matcher1 = new AntPathRequestMatcher("/test/test");
        //SecurityConfig config = new SecurityConfig("ROLE_ANONYMOUS");
        List<ConfigAttribute> configs1 = SecurityConfig.createList("0");
        //configs.add(config);
        map.put(matcher1,configs1);
        requestMap = map;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) o;
        HttpServletRequest request = fi.getRequest();
        //String url = fi.getRequestUrl();
        //String httpMethod = fi.getRequest().getMethod();

        // Lookup your database (or other source) using this information and populate the
        // list of attributes (这里初始话你的权限数据)
        //List<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();

        boolean isMatch = requestMap.keySet().stream().anyMatch(requestMatcher -> requestMatcher.matches(request));
        if (!isMatch) {
            throw new RuntimeException("can not find method");
        }
        //遍历我们初始化的权限数据，找到对应的url对应的权限
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap
                .entrySet()) {
            if (entry.getKey().matches(request)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {

        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}

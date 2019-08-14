package com.happy.config;

import com.happy.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerTokenServicesConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @author jun.wang
 * @title: ResourceServerConfig
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/4/2 13:28
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    /*@Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;

    @Autowired
    private AuthorizationServerProperties authorizationServerProperties;*/

    @Autowired
    private Environment environment;

    //@Autowired
    //private OAuth2RestOperations restTemplate;

    @Autowired
    private UserInfoRestTemplateFactory userInfoRestTemplateFactory;

    //@Autowired
    //private DynamicallyUrlInterceptor dynamicallyUrlInterceptor;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //.antMatchers("/test/tests").permitAll()//配置允许直接访问的url
                .anyRequest().permitAll()////所有url不要求权限认证
                //.anyRequest().authenticated()//所有url要求权限认证
                /*.withObjectPostProcessor(new ObjectPostProcessor<DynamicallyUrlInterceptor>() {
                    public <o extends DynamicallyUrlInterceptor> o postProcess(
                            o fsi) {
                        //fsi.setSecurityMetadataSource(new FilterSecurityMetadataSource());
                        //fsi.setAccessDecisionManager(accessDecisionManager());
                        return fsi;
                    }
                })*/
                .and()
                .httpBasic();
        http.addFilterBefore(dynamicallyUrlInterceptor(), FilterSecurityInterceptor.class);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("order"); //重点，设置资源id，oauth/check_token接口调用需配置
        //ResourceServerTokenServicesConfiguration
        resources.tokenServices(tokenServices());
    }

    @Bean
    public ResourceServerTokenServices tokenServices() {
        String clientId = environment.getProperty("security.oauth2.client.client-id");
        String userInfoUrl = environment.getProperty("security.oauth2.resource.user-info-uri");
        MyUserInfoTokenServices userInfoTokenServices = new MyUserInfoTokenServices(userInfoUrl, clientId);
        userInfoTokenServices.setRestTemplate(userInfoRestTemplateFactory.getUserInfoRestTemplate());
        return userInfoTokenServices;
    }

    /*@Bean
    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setCheckTokenEndpointUrl(authorizationServerProperties.getCheckTokenAccess());
        remoteTokenServices.setClientId(oAuth2ClientProperties.getClientId());
        remoteTokenServices.setClientSecret(oAuth2ClientProperties.getClientSecret());
        remoteTokenServices.setAccessTokenConverter(accessTokenConverter());
        return remoteTokenServices;
    }

    @Bean
    public AccessTokenConverter accessTokenConverter() {
        return new DefaultAccessTokenConverter();
    }*/

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        String clientId = environment.getProperty("security.oauth2.client.client-id");
        String userInfoUrl = environment.getProperty("security.oauth2.resource.user-info-uri");
        MyUserInfoTokenServices userInfoTokenServices = new MyUserInfoTokenServices(userInfoUrl, clientId);
        userInfoTokenServices.setRestTemplate(userInfoRestTemplateFactory.getUserInfoRestTemplate());
        List<AccessDecisionVoter<? extends Object>> decisionVoters
                = Arrays.asList(
                //new WebExpressionVoter(),
                new RoleBasedVoter(userInfoTokenServices));
        return new CommonAccessDecisionManager(decisionVoters);
    }

    @Bean
    public DynamicallyUrlInterceptor dynamicallyUrlInterceptor(){
        DynamicallyUrlInterceptor dynamicallyUrlInterceptor = new DynamicallyUrlInterceptor();
        dynamicallyUrlInterceptor.setSecurityMetadataSource(new FilterSecurityMetadataSource());
        dynamicallyUrlInterceptor.setAccessDecisionManager(accessDecisionManager());
        return dynamicallyUrlInterceptor;
    }
}



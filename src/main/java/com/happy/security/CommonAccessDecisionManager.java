package com.happy.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author jun.wang
 * @title: CommonAccessDecisionManager
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/8/9 13:50
 */
public class CommonAccessDecisionManager extends AbstractAccessDecisionManager {
    public CommonAccessDecisionManager(List<AccessDecisionVoter<?>> decisionVoters) {
        super(decisionVoters);
    }

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) throws AccessDeniedException, InsufficientAuthenticationException {
        int grant = 0;
        int abstain = 0;
        List<ConfigAttribute> singleAttributeList = new ArrayList<>(1);
        singleAttributeList.add(null);
        Iterator var7 = attributes.iterator();

        while(var7.hasNext()) {
            ConfigAttribute attribute = (ConfigAttribute)var7.next();
            singleAttributeList.set(0, attribute);
            Iterator var9 = this.getDecisionVoters().iterator();

            while(var9.hasNext()) {
                AccessDecisionVoter voter = (AccessDecisionVoter)var9.next();
                int result = voter.vote(authentication, object, singleAttributeList);
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("Voter: " + voter + ", returned: " + result);
                }

                switch(result) {
                    case -1:
                        throw new AccessDeniedException(this.messages.getMessage("AbstractAccessDecisionManager.accessDenied", "Access is denied"));
                    case 1:
                        ++grant;
                        break;
                    default:
                        ++abstain;
                }
            }
        }

        if (grant <= 0) {
            this.checkAllowIfAllAbstainDecisions();
        }
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

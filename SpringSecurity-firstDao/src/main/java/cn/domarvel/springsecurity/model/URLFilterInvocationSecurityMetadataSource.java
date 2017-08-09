package cn.domarvel.springsecurity.model;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/9.
 */
public class URLFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource,InitializingBean{
    //权限集合
    private Map<String, Collection<ConfigAttribute>> requestMap;


    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}

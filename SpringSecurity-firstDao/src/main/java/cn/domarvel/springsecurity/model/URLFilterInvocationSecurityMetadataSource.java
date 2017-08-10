package cn.domarvel.springsecurity.model;

import cn.domarvel.dao.ResMapper;
import cn.domarvel.dao.ResRoleMapper;
import cn.domarvel.po.Res;
import cn.domarvel.po.Role;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.*;

/**
 * Created by Administrator on 2017/8/9.
 */
public class URLFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource,InitializingBean{
    //权限集合
    private Map<String, Collection<ConfigAttribute>> requestMap;


    @Override
    public void afterPropertiesSet() throws Exception {
        requestMap = loadAllResource();
    }

    /**
     * 该方法用作 RequestMap 的数据刷新。
     */
    public void refreshRequestData(){
        try {
            afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private ResMapper resMapper;

    @Autowired
    private ResRoleMapper resRoleMapper;
    /**
     * 该方法目的是查询数据库里面的 String(代表URL),和该 URL 所需要的权限 Collection<ConfigAttribute>
     * @return
     */
    private Map<String,Collection<ConfigAttribute>> loadAllResource(){
        //声明一个缓存容器，缓存所有关于资源和权限的信息。
        Map<String , Collection<ConfigAttribute>> result = new HashMap<>();

        //初始化所有的资源对应的权限信息。
        List<Res> res = resMapper.findAllRes();
        for (Res resi : res) {
            //遍历URL
            String url = resi.getRes_url();

            //通过 URL 查询出所有关于该资源的权限信息。
            //这里 我把 url 封装了一下，把 url 放进了 Res 实体类里面。
            Res requestMessage = new Res("",url,"");
            List<Role> roles = resRoleMapper.findAllRolesByRes(requestMessage);

            //开始封装当前资源对应的权限信息。
            Collection<ConfigAttribute> attrs = new ArrayList<>();
            for (Role role : roles) {
                attrs.add(new SecurityConfig(role.getRname()));
            }
            result.put(url,attrs);
        }

        return result;
    }
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String url = ((FilterInvocation)object).getRequestUrl();

        System.out.println(url);
        System.out.println(requestMap.get(url));

        return requestMap.get(url);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Collection<ConfigAttribute> allConfigAttrs = new ArrayList<>();

        Set<String> set = requestMap.keySet();

        for (String s : set) {
            allConfigAttrs.addAll(requestMap.get(s));
        }

        return allConfigAttrs;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}

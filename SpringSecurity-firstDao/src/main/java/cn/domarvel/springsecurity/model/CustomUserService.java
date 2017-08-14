package cn.domarvel.springsecurity.model;

import cn.domarvel.dao.UserMapper;
import cn.domarvel.dao.UserRoleMapper;
import cn.domarvel.po.Role;
import cn.domarvel.pocustom.UserCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/8/9.
 */
@Component("customUserService")
public class CustomUserService implements UserDetailsService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserCustom requestMessage = new UserCustom(username,"");

        System.out.println(userMapper);
        cn.domarvel.po.User resultUser = userMapper.findUserByUsername(requestMessage);
        System.out.println("========="+resultUser);
        if (resultUser == null) {
            return null;
        }
        String password = resultUser.getPassword();
        boolean enabled = true;
        boolean accountNonLoked = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExipred = true;

        Collection<GrantedAuthority> authorities = new ArrayList<>();

        List<Role> roles = userRoleMapper.findRolesByUsername(requestMessage);

        if (roles == null) {
            return null;
        }
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRname()));
        }

        User user = new User(username,password,enabled,accountNonExpired,credentialsNonExipred,accountNonLoked,authorities);
        return user;
    }
}

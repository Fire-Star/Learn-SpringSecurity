package cn.domarvel.service;

import cn.domarvel.dao.UserMapper;
import cn.domarvel.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/8/14.
 */
@Service
public class TestService {

    @Autowired
    private UserMapper userMapper;

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public User findUserByUsername(User user){
        return  userMapper.findUserByUsername(user);
    }
}

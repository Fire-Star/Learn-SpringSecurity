package cn.domarvel.dao;

import cn.domarvel.po.User;
import cn.domarvel.pocustom.UserCustom;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by Administrator on 2017/8/9.
 */
public interface UserMapper {
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public User findUserByUsername(User user);
}

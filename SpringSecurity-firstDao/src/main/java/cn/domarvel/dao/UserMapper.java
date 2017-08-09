package cn.domarvel.dao;

import cn.domarvel.po.User;
import cn.domarvel.pocustom.UserCustom;

/**
 * Created by Administrator on 2017/8/9.
 */
public interface UserMapper {
    public User findUserByUsername(User user);
}

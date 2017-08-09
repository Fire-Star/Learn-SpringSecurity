package cn.domarvel.dao;

import cn.domarvel.po.Role;
import cn.domarvel.pocustom.UserCustom;

import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 */
public interface RoleMapper {
    public List<Role> findAllRoles();
}

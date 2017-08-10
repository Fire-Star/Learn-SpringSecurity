package cn.domarvel.dao;

import cn.domarvel.po.Res;
import cn.domarvel.po.Role;

import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 */
public interface ResRoleMapper {
    public List<Role> findAllRolesByRes(Res res);
}

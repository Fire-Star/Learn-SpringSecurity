package cn.domarvel.web.controller;

import cn.domarvel.dao.RoleMapper;
import cn.domarvel.dao.UserMapper;
import cn.domarvel.dao.UserRoleMapper;
import cn.domarvel.po.Role;
import cn.domarvel.po.User;
import cn.domarvel.pocustom.UserCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 */
@Controller
public class TestUserController {
    /**
     * 该类用来测试数据库的相关访问。
     */

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @RequestMapping("/test/showAllUser")
    public void showAllUser(HttpServletResponse response){
        UserCustom userCustom = new UserCustom("MoonFollow","");
        User user = userMapper.findUserByUsername(userCustom);
        try {
            response.getWriter().println(user.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/test/showAllRoles")
    public void showAllRoles(HttpServletResponse response){
        //设置响应的编码以及响应内容编码。
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        //向页面输出内容。
        List<Role> roles = roleMapper.findAllRoles();
        for (Role role : roles) {
            try {
                response.getWriter().println(role.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("/test/showUserRolesByUsername")
    public void showUserRolesByUsername(HttpServletResponse response,UserCustom userCustom){
        //设置响应的编码以及响应内容编码。
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        List<Role> roles = userRoleMapper.findRolesByUsername(userCustom);

        if (roles != null && roles.size()==0) {
            try {
                response.getWriter().println("你还没有对 "+userCustom.getUsername()+" 分配权限！！！");
                return ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (Role role : roles) {
            try {
                response.getWriter().println(userCustom.getUsername()+"<---->"+role.getRname());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

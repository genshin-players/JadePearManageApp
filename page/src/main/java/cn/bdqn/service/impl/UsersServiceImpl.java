package cn.bdqn.service.impl;

import cn.bdqn.entity.Users;
import cn.bdqn.mapper.UsersMapper;
import cn.bdqn.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户（所有学生，老师，管理员） 服务实现类
 * </p>
 *
 * @author 
 * @since 2023-07-11
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Autowired
    private DefaultWebSecurityManager securityManager;


    public void userLogin(String username, String password) {
        // 1.将SecurityManager对象设置到运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        // 2.获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        // 3.将前端传来的用户名密码封装为Shiro提供的身份对象
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        // 4.Shiro认证
        subject.login(token);
    }



}

package cn.bdqn.service;

import cn.bdqn.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户（所有学生，老师，管理员） 服务类
 * </p>
 *
 * @author 
 * @since 2023-07-11
 */
public interface UsersService extends IService<Users> {
    public void userLogin(String username, String password,String rememberMe);


}

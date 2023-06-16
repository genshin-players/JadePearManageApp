package cn.bdqn.service;

import cn.bdqn.dto.User_ClassDTO;
import cn.bdqn.entity.Users;
import cn.bdqn.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 用户（所有学生，老师，管理员） 服务类
 * </p>
 *
 * @author ljj
 * @since 2023-06-11
 */
public interface IUsersService extends IService<Users> {



    Users showUserById(Integer id);

    boolean updateUSers(Integer id, Users users);



}

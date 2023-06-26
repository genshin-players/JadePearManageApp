package cn.bdqn.service.impl;

import cn.bdqn.entity.Users;
import cn.bdqn.mapper.UsersMapper;
import cn.bdqn.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户（所有学生，老师，管理员） 服务实现类
 * </p>
 *
 * @author dddqmmx
 * @since 2023-06-09
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}

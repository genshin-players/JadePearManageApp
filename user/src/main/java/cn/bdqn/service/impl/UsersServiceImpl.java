package cn.bdqn.service.impl;

import cn.bdqn.dto.User_ClassDTO;
import cn.bdqn.entity.Users;
import cn.bdqn.entity.Users;

import cn.bdqn.mapper.UsersMapper;
import cn.bdqn.service.IUsersService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 用户（所有学生，老师，管理员） 服务实现类
 * </p>
 *
 * @author ljj
 * @since 2023-06-11
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    @Autowired
    private  UsersMapper usersMapper;




    @Override
    public Users showUserById(Integer id) {
        Users users = usersMapper.selectById(id);
        return users;
    }

    @Override
    public boolean updateUSers(Integer id, Users users) {
        if (usersMapper.updateById(users)>0){
            return true;
        }
        return  false;
    }


    /**
     * 登录方法
     *
     * @param userName
     * @param password
     */
    @Override
    public Users login(String userName, String password) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userName);
        Users users = usersMapper.selectOne(queryWrapper);
        if (users!=null && password.equals(users.getPassword())){
            return users;
        }
        return null;
    }
}

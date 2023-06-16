package cn.bdqn.controller;

import cn.bdqn.entity.Users;
import cn.bdqn.service.IUsersService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ZedFeorius
 * @version 1.0.0
 * @date 06-16-2023  10-05-27
 * @packageName cn.bdqn.controller
 * @className PublicController
 * @describe TODO
 */

/**
 * 提供给其他模块的接口
 */
@RestController
@CrossOrigin
@RequestMapping("/user/public")
public class PublicController {
    @Autowired
    IUsersService usersService;

    /**
     * 根据id获得user对象
     *
     * @param userId 用户id
     * @return 用户对象
     */
    @GetMapping("/getUserById")
    public Users getUserById(Integer userId) {
        return usersService.getById(userId);
    }

    @GetMapping("/getAllMember")
    public List<Users> getAllMember() {
        return usersService.list(new QueryWrapper<Users>().eq("role_id", 5));
    }
}


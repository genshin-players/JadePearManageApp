package cn.bdqn.controller;

import cn.bdqn.entity.Roles;
import cn.bdqn.entity.Users;
import cn.bdqn.service.IRolesService;
import cn.bdqn.service.IUsersService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 小程序接口
 */
@CrossOrigin
@RestController
@RequestMapping("/mp-user")
public class MpController {

    @Autowired
    IUsersService usersService;
    @Autowired
    IRolesService rolesService;


    @PostMapping("/login")
    public Map<String,Object> login(String userName, String password){
        Map<String,Object>map=new HashMap<>();
        try {
            Users login = usersService.login(userName, password);

            if(login!=null){
                Roles roles=rolesService.getById(login.getRoleId());
                map.put("roles",roles);
            }
            map.put("code",200);
            map.put("msg","success");
            map.put("data",login);

        }catch (Exception e){
            e.printStackTrace();
            map.put("code",500);
            map.put("msg","error");
        }
        return map;
    }


    /**
     * 比较传过来的密码是否和数据库中的一致
     * @return
     */
    @PostMapping("/compareOldPwd")
    public Map<String,Object> getUserById(String userId,String oldPwd){
            Map<String,Object>map=new HashMap<>();

        try {
            Users user= usersService.getById(Integer.valueOf(userId));
            if(oldPwd.equals(user.getPassword())){
                map.put("data","true");
                map.put("code",200);
                map.put("msg","success");
            }else {
                map.put("data","false");
                map.put("code",500);
                map.put("msg","error");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",500);
            map.put("msg","error");
        }
        return map;
    }

    @PostMapping("/updateUserPwd")
    public Map<String,Object> updateUserPwd(String userId,String newPwd){
        Map<String,Object>map=new HashMap<>();

        try {
            Users user= usersService.getById(Integer.valueOf(userId));
            user.setPassword(newPwd);
            usersService.updateById(user);
            map.put("data","true");
            map.put("code",200);
            map.put("msg","error");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",500);
            map.put("msg","error");
        }
        return map;
    }
}

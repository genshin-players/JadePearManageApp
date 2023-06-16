package cn.bdqn.controller;



import cn.bdqn.entity.Classes;
import cn.bdqn.entity.Users;
import cn.bdqn.service.IClassesService;
import cn.bdqn.service.IStudentClassService;
import cn.bdqn.service.IUsersService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.UpdateChainWrapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户（所有学生，老师，管理员） 前端控制器
 * </p>
 *
 * @author ljj
 * @since 2023-06-11
 */

@RestController

@RequestMapping("/user")
public class UsersController {

    @Autowired
    private IUsersService usersService;

    @Autowired
    private IStudentClassService studentClassService;

    @Autowired
    private IClassesService classesService;

    @ResponseBody
    @RequestMapping("showTeacher")
    private  List<Users> showTeacher(){
        QueryWrapper<Users> wrapper=new QueryWrapper<>();
        wrapper.eq("role_id",3);
        //Map<String,Object> map = new HashMap<>();
        List<Users> list = usersService.list(wrapper);


        return list;
    }

    @RequestMapping("showStudent")
    private  List<Users> showStudent(){
        QueryWrapper<Users> wrapper=new QueryWrapper<>();
        wrapper.eq("role_id",6);
        List<Users> list = usersService.list(wrapper);
        return list;
    }



    @RequestMapping("deleteTeacher_Student")
    private Map<String,Object> deleteTeacher(Integer id){
        Map<String,Object>map=new HashMap<>();
        QueryWrapper<Users> wrapper=new QueryWrapper<>();
        wrapper.eq("id",id);
        boolean remove = usersService.remove(wrapper);
        if (remove){
            map.put("data",remove);
            map.put("msg","success");
            map.put("code","200");
        }else {
            map.put("msg","error");
            map.put("code","500");
        }
        return map;
    }


    @ResponseBody
    @RequestMapping("addUser")
    private Map<String,Object> addUser(@RequestBody  Users users){
        Map<String,Object>map=new HashMap<>();
        boolean b = usersService.save(users);
        if (b){
            map.put("data",b);
            map.put("msg","success");
            map.put("code","200");
        }else {
            map.put("msg","error");
            map.put("code","500");
        }
        return map;

    }

    //查询所有的班级
    @ResponseBody
    @RequestMapping("showClass")
    private List<Classes> showClass(){
        Map<String,Object>map=new HashMap<>();
        List<Classes> list = classesService.list();
        return list;
    }


    @ResponseBody
    @RequestMapping("updateUser")
    private Map<String,Object> updateUser(@RequestBody  Users users){
        Map<String,Object>map=new HashMap<>();
        boolean b = usersService.updateById(users);
        if (b){
            map.put("data",b);
            map.put("msg","success");
            map.put("code","200");
        }else {
            map.put("msg","error");
            map.put("code","500");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("showUserById")
    private Users showUserById(Integer id){

        Users users = usersService.showUserById(id);
        return users;

    }












    /*ly所需接口*/
    @ResponseBody
    @RequestMapping("selectUsersById")
    private  Map<String,Object> selectUsersById(Integer id){
        Map<String,Object>map=new HashMap<>();
        Users byId = usersService.getById(id);
        if (byId!=null){
            map.put("data",byId);
            map.put("msg","success");
            map.put("code","200");
        }else {
            map.put("msg","error");
            map.put("code","500");
        }
        return map;
    }








}

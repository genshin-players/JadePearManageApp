package cn.bdqn.controller;



import cn.bdqn.dto.User_ClassDTO;
import cn.bdqn.entity.Classes;
import cn.bdqn.entity.StudentClass;
import cn.bdqn.entity.Users;
import cn.bdqn.mapper.UsersMapper;
import cn.bdqn.service.IClassesService;
import cn.bdqn.service.IStudentClassService;
import cn.bdqn.service.IUsersService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.UpdateChainWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.netflix.discovery.converters.Auto;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



import java.util.ArrayList;
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
    private UsersMapper usersMapper;

    @Autowired
    private IStudentClassService studentClassService;

    @Autowired
    private IClassesService classesService;

    @ResponseBody
    @RequestMapping("showAll")
    private  List<Users> showAll(){
        List<Users> list = usersService.list();
        return list;

    }


    @ResponseBody
    @RequestMapping("ShowCountT")
    private  Integer ShowCount(){
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("role_id",3);
        Integer integer = usersMapper.selectCount(queryWrapper);
        return integer;

    }

    @ResponseBody
    @RequestMapping("ShowCountS")
    private  Integer ShowCountS(){
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("role_id",6);
        Integer integer = usersMapper.selectCount(queryWrapper);
        return integer;

    }

    @ResponseBody
   @RequestMapping("showTeacher")
   private  List<User_ClassDTO> showTeacher(
            @RequestParam(value = "username",required = false) String username,
            @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum
    ){

        PageHelper.startPage(pageNum, 2);
       //展示数据的集合
       List<User_ClassDTO> showAll=new ArrayList<>();

        QueryWrapper<Users> wrapper=new QueryWrapper<>();

        PageInfo pageInfo = null;
       if (username==null || "".equals(username)){
           wrapper.eq("role_id",3);
           //Map<String,Object> map = new HashMap<>();
           List<Users> list = usersService.list(wrapper);

           for (Users users : list) {
               User_ClassDTO userClassDTO=new User_ClassDTO();
               BeanUtils.copyProperties(users,userClassDTO);
               LambdaQueryWrapper<StudentClass> lambdaQueryWrapper = new LambdaQueryWrapper<>();
               lambdaQueryWrapper.eq(StudentClass::getStudentId,users.getId());
               //学生对应班级
               StudentClass studentClass = studentClassService.getOne(lambdaQueryWrapper);
               if (users.getId()==studentClass.getStudentId()){
                   userClassDTO.setClassId(studentClass.getClassId());
               }
               //班级名称
               Classes classes = classesService.selectClassById(studentClass.getClassId());
               if (userClassDTO.getClassId()== classes.getId()){
                   userClassDTO.setName(classes.getName());
               }
               showAll.add(userClassDTO);
               pageInfo = new PageInfo(showAll);
           }
       }else {
           LambdaQueryWrapper<Users> QueryWrapper = new LambdaQueryWrapper<>();
           QueryWrapper.like(Users::getUsername, "%"+username+"%");
           QueryWrapper.eq(Users::getRoleId,3);
           List<Users> list = usersService.list(QueryWrapper);
           if (list!=null){
               for (Users users : list) {
                   User_ClassDTO userClassDTO=new User_ClassDTO();
                   BeanUtils.copyProperties(users,userClassDTO);
                   LambdaQueryWrapper<StudentClass> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                   lambdaQueryWrapper.eq(StudentClass::getStudentId,users.getId());
                   //学生对应班级
                   StudentClass studentClass = studentClassService.getOne(lambdaQueryWrapper);
                   if (users.getId()==studentClass.getStudentId()){
                       userClassDTO.setClassId(studentClass.getClassId());
                   }
                   //班级名称
                   Classes classes = classesService.selectClassById(studentClass.getClassId());
                   if (userClassDTO.getClassId()== classes.getId()){
                       userClassDTO.setName(classes.getName());
                   }
                   showAll.add(userClassDTO);
                   pageInfo = new PageInfo(showAll);
               }
           }



       }
       if ("".equals(pageInfo.getList())){
           return pageInfo.getList();
       }else {
           return showAll;
       }

   }


    @RequestMapping("showStudent")
    private  List<User_ClassDTO> showStudent(
            @RequestParam(value = "username",required = false) String username,
            @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum
    ){
        PageHelper.startPage(pageNum, 2);
        //展示数据的集合
        List<User_ClassDTO> showAll=new ArrayList<>();



        QueryWrapper<Users> wrapper=new QueryWrapper<>();
        PageInfo pageInfo = null;
        if (username==null || "".equals(username)){
            wrapper.eq("role_id",6);
            //Map<String,Object> map = new HashMap<>();
            List<Users> list = usersService.list(wrapper);

            for (Users users : list) {
                User_ClassDTO userClassDTO=new User_ClassDTO();
                BeanUtils.copyProperties(users,userClassDTO);


                LambdaQueryWrapper<StudentClass> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                lambdaQueryWrapper.eq(StudentClass::getStudentId,users.getId());

                //学生对应班级
                StudentClass studentClass = studentClassService.getOne(lambdaQueryWrapper);


                if (users.getId()==studentClass.getStudentId()){
                    userClassDTO.setClassId(studentClass.getClassId());
                }
                //班级名称
                Classes classes = classesService.selectClassById(studentClass.getClassId());
                if (userClassDTO.getClassId()== classes.getId()){
                    userClassDTO.setName(classes.getName());
                }
                showAll.add(userClassDTO);
                pageInfo = new PageInfo(showAll);

            }
        }else {
            LambdaQueryWrapper<Users> QueryWrapper = new LambdaQueryWrapper<>();
            QueryWrapper.like(Users::getUsername, "%"+username+"%");
            QueryWrapper.eq(Users::getRoleId,6);
            List<Users> list = usersService.list(QueryWrapper);
            if (list!=null){
                for (Users users : list) {
                    User_ClassDTO userClassDTO=new User_ClassDTO();
                    BeanUtils.copyProperties(users,userClassDTO);
                    LambdaQueryWrapper<StudentClass> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                    lambdaQueryWrapper.eq(StudentClass::getStudentId,users.getId());
                    //学生对应班级
                    StudentClass studentClass = studentClassService.getOne(lambdaQueryWrapper);
                    if (users.getId()==studentClass.getStudentId()){
                        userClassDTO.setClassId(studentClass.getClassId());
                    }
                    //班级名称
                    Classes classes = classesService.selectClassById(studentClass.getClassId());
                    if (userClassDTO.getClassId()== classes.getId()){
                        userClassDTO.setName(classes.getName());
                    }
                    showAll.add(userClassDTO);
                    pageInfo = new PageInfo(showAll);
                }
            }

        }
        if ("".equals(pageInfo.getList())){
            return pageInfo.getList();
        }else {
            return showAll;
        }
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
    @ResponseBody
    @RequestMapping("addStudentClass")
    private Map<String,Object> addUser(@RequestBody  StudentClass studentClass){
        Map<String,Object>map=new HashMap<>();
        boolean b = studentClassService.saveOrUpdate(studentClass);
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


    @RequestMapping("updateUser")
    private Map<String,Object> updateUser(@RequestBody Users users/*, @RequestParam(value = "id") Integer id*/){
        Map<String,Object>map=new HashMap<>();
      /*  //更新的条件
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);*/
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

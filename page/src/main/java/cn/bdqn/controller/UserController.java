package cn.bdqn.controller;

import cn.bdqn.client.UserClient;
import cn.bdqn.dto.User_ClassDTO;
import cn.bdqn.entity.Classes;
import cn.bdqn.entity.StudentClass;
import cn.bdqn.entity.Users;

import cn.bdqn.vo.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller

@CrossOrigin
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserClient userClient;


    @RequestMapping("show_teacher")
    public String showTeacher(
            @RequestParam(value = "username",required = false) String username,
            @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
            Model model) {
        List<User_ClassDTO> user_classDTOS = userClient.showTeacher(username, pageNum);

        Integer integer = userClient.ShowCountT();

        model.addAttribute("users",user_classDTOS);
        model.addAttribute("pages",integer);
        model.addAttribute("username",username);
        model.addAttribute("now",pageNum);
        return "teacher/show_teacher";
    }


    @RequestMapping("/show_student")
    public String  showStudent(
            @RequestParam(value = "username",required = false) String username,
            @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
            Model model){
        List<User_ClassDTO> users = userClient.showStudent(username, pageNum);

        Integer integer = userClient.ShowCountS();
        model.addAttribute("users",users);
        model.addAttribute("pages",integer);
        model.addAttribute("username",username);
        model.addAttribute("now",pageNum);
        return "student/show_student";
    }
//=============================================================================================
    @RequestMapping("/deleteTeacher")
    public String  deleteTeacher(@RequestParam(value = "id") Integer id){
        Map<String, Object> map = userClient.deleteTeacher(id);
        System.out.println("删除成功");
        System.out.println(id);

        return "redirect:show_teacher";
    }

    @RequestMapping("/deleteStudent")
    public String   deleteStudent(Integer id){
        Map<String, Object> map= userClient.deleteTeacher(id);
        return "redirect:show_student";
    }

//========================================================================================


    @PostMapping("/add_Tuser")
    public  String addTUser(@RequestParam(value = "username")String username,
                           @RequestParam(value = "realname") String  realname,
                           @RequestParam(value = "class_id") Integer  class_id,
                           @RequestParam(value = "age") String age,
                           @RequestParam(value = "gender")String gender,
                           @RequestParam(value = "phone") String phone,
                           @RequestParam(value = "identity") String identity,
                            @RequestParam(value = "account_info") String account_info
    ){

        Users users=new Users();
        users.setUsername(username);
        users.setPassword("123456");
        users.setCreateTime(new Date());
        users.setUpdateTime(new Date());
        users.setAccountInfo(account_info);
        users.setRoleId(3);
        users.setIdentityInfo("{"+
                '"'+"realname"+'"'+":"+'"'+realname+'"'+","+
                '"'+"age"+'"'+":"+'"'+age+'"'+","+
                '"'+"gender"+'"'+":"+'"'+gender+'"'+","+
                '"'+"phone"+'"'+":"+'"'+phone+'"'+","+
                '"'+"identity"+'"'+":"+'"'+identity+'"'
                +"}");

        Map<String, Object> map = userClient.addUser(users);
        //==================================================
        List<Users> users1 = userClient.showAll();
        Users users2 = users1.get(users1.size() - 1);
        //========================================================


        //===========================================================
        //添加Class——Student  表
        StudentClass studentClass=new StudentClass();
        studentClass.setClassId(class_id);
        studentClass.setStudentId(users2.getId());
        userClient.addStudentClass(studentClass);
        //===============================================================


        return "redirect:show_teacher";
    }

    @PostMapping("/add_Suser")
    public  String addSUser(@RequestParam(value = "username")String username,
                           @RequestParam(value = "realname") String  realname,
                            @RequestParam(value = "class_id") Integer  class_id,
                           @RequestParam(value = "age") String age,
                           @RequestParam(value = "gender")String gender,
                           @RequestParam(value = "phone") String phone,
                           @RequestParam(value = "identity") String identity,
                            @RequestParam(value = "account_info") String account_info
                            ){
        Users users=new Users();
        users.setUsername(username);
        users.setPassword("123456");
        users.setCreateTime(new Date());
        users.setUpdateTime(new Date());
        users.setRoleId(6);
        users.setAccountInfo(account_info);
        users.setIdentityInfo("{"+
                '"'+"realname"+'"'+":"+'"'+realname+'"'+","+
                '"'+"age"+'"'+":"+'"'+age+'"'+","+
                '"'+"gender"+'"'+":"+'"'+gender+'"'+","+
                '"'+"phone"+'"'+":"+'"'+phone+'"'+","+
                '"'+"identity"+'"'+":"+'"'+identity+'"'
                +"}");

        Map<String, Object> map = userClient.addUser(users);
        //==================================================
        List<Users> users1 = userClient.showAll();
        Users users2 = users1.get(users1.size() - 1);
        System.out.println("111111111111111111111"+users2.getId());
        //========================================================


        //===========================================================
        //添加Class——Student  表
        StudentClass studentClass=new StudentClass();
        studentClass.setClassId(class_id);
        studentClass.setStudentId(users2.getId());
        userClient.addStudentClass(studentClass);
        //===============================================================


        return "redirect:show_student";
    }



    @RequestMapping("/showUser_ById")
    private String showUserById(@RequestParam(value = "id") Integer id,Model model){
        Users users = userClient.showUserById(id);
        model.addAttribute("users",users);
        return "teacher/addteacher";
    }

   /* @ResponseBody*/
    @RequestMapping("/update_User")
    public String updateUser(@RequestParam(value = "id")Integer id,
                             @RequestParam(value = "username")String username,
                             @RequestParam(value = "realname") String  realname,
                             @RequestParam(value = "age") String age,
                             @RequestParam(value = "gender")String gender,
                             @RequestParam(value = "phone") String phone,
                             @RequestParam(value = "identity") String identity,
                             Model model){
        Users users=new Users();
        users.setId(id);
        users.setUsername(username);
        users.setIdentityInfo("{"+
                '"'+"realname"+'"'+":"+'"'+realname+'"'+","+
                '"'+"age"+'"'+":"+'"'+age+'"'+","+
                '"'+"gender"+'"'+":"+'"'+gender+'"'+","+
                '"'+"phone"+'"'+":"+'"'+phone+'"'+","+
                '"'+"identity"+'"'+":"+'"'+identity+'"'
                +"}");
        System.out.println(id);
        System.out.println(realname);
        System.out.println(users);
         userClient.updateUser(users/*, users.getId()*/);

        return "teacher/update_addteacher";

    }






    //查询所有的班级
   /* @ResponseBody
    @RequestMapping("showClass")
    private List<Classes> addUser(Model model){
        List<Classes> classes = userClient.showClass();
        model.addAttribute("className",classes);
        return ""

    }*/






    /*ly所需接口*/
    @RequestMapping("/user/selectUsersById")
    public String selectUsersById(Integer id,Model model){
        System.out.println("sb ");
        Users users = userClient.selectUsersById(id);
        System.out.println(users.getId()+"今天天气真好");
        model.addAttribute("usersById",users);
        System.out.println(users.getUsername()+"hhhhhhhhhhhhhhhhhhhh");
        return "messages/update_Select";
    }


}

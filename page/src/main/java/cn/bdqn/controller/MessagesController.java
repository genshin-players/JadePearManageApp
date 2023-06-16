package cn.bdqn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin
public class MessagesController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/index")
    public String toIndex(){
        return "redirect:/";
    }
    //去往个人信息页面
    @RequestMapping("Name")
    public String Name(){
        return "app-profile";
    }
    //去往个人信息/去往修改密码
    @RequestMapping("Password")
    public String Password(){
        return "messages/update_Password";
    }
    //去往个人信息/查看个人详情页面

    @RequestMapping("Select")
    public String Select(@RequestParam(defaultValue = "1") String id){
        return "redirect:/user/getUsersById?id="+id;
    }
    //去往个人信息/头像照片
    @RequestMapping("Images")
    public String Images(){
        return "messages/update_Images";
    }
    //去往个人信息/账号资料
    @RequestMapping("User")
    public String User(){
        return "messages/update_User";
    }
}

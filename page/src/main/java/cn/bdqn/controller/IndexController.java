package cn.bdqn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@CrossOrigin
public class IndexController {
    @RequestMapping("update_addteacher")
    public String update_addteacher(){
        return "teacher/update_addteacher";
    }
    @RequestMapping("show_teacher")
    public String showteacher(){
        return "teacher/show_teacher";
    }
    @RequestMapping("update_addstudent")
    public String update_addstudent(){
        return "student/update_addstudent";
    }
    @RequestMapping("show_student")
    public String showstudent(){
        return "student/show_student";
    }
}

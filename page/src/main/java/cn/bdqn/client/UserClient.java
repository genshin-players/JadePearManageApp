package cn.bdqn.client;

import cn.bdqn.dto.User_ClassDTO;
import cn.bdqn.entity.Classes;
import cn.bdqn.entity.StudentClass;
import cn.bdqn.entity.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@Component
@FeignClient(value="userabc")
public interface UserClient {

    @RequestMapping("/user/showTeacher")
    List<User_ClassDTO> showTeacher(
            @RequestParam(value = "username",required = false) String username,
            @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum
    );


    @RequestMapping("/user/showStudent")
    List<User_ClassDTO> showStudent();


    @RequestMapping("user/deleteTeacher_Student")
    Map<String, Object>  deleteTeacher(@RequestParam(value = "id")  Integer id);

    @PostMapping("/user/addUser")
    Map<String,Object>  addUser(@RequestBody  Users users);



    @RequestMapping("/user/ShowCount")
      Integer ShowCount();

    @RequestMapping("/user/showAll")
    List<Users> showAll();


    @RequestMapping("/user/addStudentClass")
     Map<String,Object> addStudentClass(@RequestBody StudentClass studentClass);

    @ResponseBody
    @RequestMapping("/user/showUserById")
     Users showUserById(@RequestParam(value = "id") Integer id);



 /*   //查询所有的班级
    @RequestMapping("/user/showClass")
    List<Classes> showClass();
*/



    @RequestMapping("/user/updateUser")
    Map<String,Object> updateUser(@RequestBody Users users/*,@RequestParam(value = "id") Integer id*/);



    /*ly所需接口*/
    @RequestMapping("/user/selectUsersById")
    Users selectUsersById(@RequestParam(value = "id")Integer id);


    /**
     * 提供给其他模块的接口
     */
    @GetMapping("/user/public/getUserById")
    public Users getUserById(@RequestParam(value = "userId")Integer userId);


    @GetMapping("/user/public/getAllMember")
    public List<Users> getAllMember();

}

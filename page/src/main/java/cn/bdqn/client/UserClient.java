package cn.bdqn.client;

import cn.bdqn.entity.Classes;
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
    List<Users> showTeacher();


    @RequestMapping("/user/showStudent")
    List<Users> showStudent();


    @RequestMapping("user/deleteTeacher_Student")
    Map<String, Object>  deleteTeacher(@RequestParam(value = "id")  Integer id);

    @PostMapping("/user/addUser")
    Map<String,Object>  addUser(@RequestBody  Users users);

    @ResponseBody
    @RequestMapping("/user/showUserById")
     Users showUserById(@RequestParam(value = "id") Integer id);



 /*   //查询所有的班级
    @RequestMapping("/user/showClass")
    List<Classes> showClass();
*/

    @ResponseBody
    @RequestMapping("/user/updateUser")
    Map<String,Object> updateUser(@RequestBody  Users users);



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

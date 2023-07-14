package cn.bdqn.controller;

import cn.bdqn.entity.Menu;
import cn.bdqn.entity.Roles;
import cn.bdqn.entity.Users;
import cn.bdqn.service.MenuService;
import cn.bdqn.service.RolesService;
import cn.bdqn.service.UsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/shiro")
public class LoginController {
    @Autowired
    private RolesService rolesService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private MenuService menuService;
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "index/login";
    }

    /**
     * 数据库登录操作
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestBody @RequestParam("username") String username,
                        @RequestParam("password") String password,String rememberMe) {
        try {
            usersService.userLogin(username,password,rememberMe);
            return "redirect:/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "index/login";
        }
    }
    @RequestMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:/shiro/toLogin";
    }


    @GetMapping("/getLoginUser")
    @ResponseBody
    public Users getLoginUser(){
        return (Users) SecurityUtils.getSubject().getPrincipal();
    }

    @RequestMapping("/notFound")
    public String notFound(){
        return "index/error404";
    }

    @ResponseBody
    @GetMapping("/getAllRoles")
    public List<Roles> getAllRoles(){
        return rolesService.list();
    }


    @ResponseBody
    @PostMapping("/getMenuByUser")
    public List<Menu> getMenuByUser(Integer userId){
        return menuService.getMenuByUserId(userId);
    }

}

package cn.bdqn.realm;

import  cn.bdqn.entity.Menu;
import cn.bdqn.entity.Users;
import cn.bdqn.mapper.MenuMapper;
import cn.bdqn.mapper.UsersMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    UsersMapper usersMapper;
    //自定义认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户输入的用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();

        //根据用户查询用户信息
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        Users users = usersMapper.selectOne(wrapper);

        //将查询到的管理员封装为认证信息
        if(users == null) {
            throw new UnknownAccountException("用户不存在");
        }

        //参数1查询的用户信息
        //参数2查询的用户密码
        //参数3加盐操作
        //参数4Realm名称
        return new SimpleAuthenticationInfo(users, users.getPassword(),
                ByteSource.Util.bytes(users.getSalt()),"myRealm");

    }

    @Autowired
    private MenuMapper menuMapper;
    //自定义授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前用户
        Users users= (Users) principalCollection.getPrimaryPrincipal();
        //从数据库中查询该用户权限
        List<Menu> menuList = menuMapper.getMenuByUserId(users.getId());
        //遍历菜单，将此用户菜单交给shiro管理
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        for(Menu menu:menuList){
            info.addStringPermission(menu.getPath());
        }
        return info;
    }


}

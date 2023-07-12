package cn.bdqn.config;

import cn.bdqn.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    // 配置加密算法
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //加密算法类型是MD5
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //加密次数5次
        hashedCredentialsMatcher.setHashIterations(5);
        return hashedCredentialsMatcher;
    }


    //获取SecurityManager对象
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(MyRealm myRealm) {
        DefaultWebSecurityManager defaultSecurityManager=new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(myRealm);
        return defaultSecurityManager;
    }

    @Bean
    public MyRealm myRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
        MyRealm myRealm = new MyRealm();
        //设置加密算法
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return myRealm;
    }

    //配置过滤器
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        //创建过滤器工厂
        ShiroFilterFactoryBean filterFactory = new ShiroFilterFactoryBean();
        //过滤器工厂设置SecurityManager
        filterFactory.setSecurityManager(defaultWebSecurityManager);
        //设置shiro的拦截规则
        Map<String,String>   filterMap = new HashMap<>();
        //不拦截的相关资源
        filterMap.put("/shiro/login", "anon");
        filterMap.put("/shiro/toLogin", "anon");
        //filterMap.put("/shiro/getLoginUser", "anon");
        filterMap.put("/shiro/notFound", "anon");
        filterMap.put("/css/**", "anon");
        filterMap.put("/js/**", "anon");
        filterMap.put("/files/**", "anon");
        filterMap.put("/font/**", "anon");
        filterMap.put("/image/**", "anon");
        filterMap.put("/picture/**", "anon");


        //其余资源都需要认证
        filterMap.put("/**", "authc");
        //讲拦截规则设置给过滤器工厂
        filterFactory.setFilterChainDefinitionMap(filterMap);
        //如果请求被拦截就会返回登录页面
        filterFactory.setLoginUrl("/shiro/toLogin");
        return filterFactory;
    }



}

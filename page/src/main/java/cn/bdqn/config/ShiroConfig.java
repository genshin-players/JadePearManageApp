package cn.bdqn.config;

import cn.bdqn.listener.MySessionListener;
import cn.bdqn.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public DefaultWebSecurityManager defaultWebSecurityManager(MyRealm myRealm, SessionManager sessionManage
    , CookieRememberMeManager cookieRememberMeManager) {
        DefaultWebSecurityManager defaultSecurityManager=new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(myRealm);

        //设置sessionManager管理器
        defaultSecurityManager.setSessionManager(sessionManage);

        //设置cookie管理器
        defaultSecurityManager.setRememberMeManager(cookieRememberMeManager);

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

        //鉴权过滤器
        filterMap.put("/user/show_teacher","perms[/user/show_teacher]");
        filterMap.put("/user/show_student","perms[/user/show_student]");
        filterMap.put("/display/daily_info","perms[/display/daily_info]");
        filterMap.put("/display/inner_activities","perms[/display/inner_activities]");
        filterMap.put("/display/external_performance","perms[/display/external_performance]");
        filterMap.put("/toMemberWork","perms[/toMemberWork]");
        filterMap.put("/toStuAttendance","perms[/toStuAttendance]");
        filterMap.put("/toAssignMoreWork","perms[/toAssignMoreWork]");
        filterMap.put("/Name","perms[/Name]");
        filterMap.put("/Select","perms[/Select]");
        filterMap.put("/User","perms[/User]");
        filterMap.put("/Password","perms[/Password]");
        filterMap.put("/Images","perms[/Images]");


        //其余资源都需要认证
        filterMap.put("/**", "user");
        //讲拦截规则设置给过滤器工厂
        filterFactory.setFilterChainDefinitionMap(filterMap);
        //如果请求被拦截就会返回登录页面
        filterFactory.setLoginUrl("/shiro/toLogin");
        //配置权限不足的页面
        filterFactory.setUnauthorizedUrl("/shiro/notFound");

        return filterFactory;
    }

    @Bean
    public SessionManager sessionManager(MySessionListener sessionListener) {
        // 创建会话管理器
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 创建会话监听器集合
        List<SessionListener> listeners = new ArrayList<>();
        listeners.add(sessionListener);    //把会话监听器加入集合
        // 将监听器集合设置到会话管理器中
        sessionManager.setSessionListeners(listeners);
        // 全局会话超时时间（单位毫秒），默认30分钟
        sessionManager.setGlobalSessionTimeout(60 * 60 * 1000);
        // 是否开启删除无效的session对象，默认为true
        sessionManager.setDeleteInvalidSessions(true);
        // 是否开启定时调度器进行检测过期session，默认为true
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }


    // 配置Cookie生成器和记住我管理器
    @Bean
    public SimpleCookie simpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //设置cookie的有效时间
        simpleCookie.setMaxAge(60*60*24*7);
        return simpleCookie;
    }

    // 配置记住我管理器
    @Bean
    public CookieRememberMeManager cookieRememberMeManager(SimpleCookie simpleCookie) {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        //设置cookie生成器
        cookieRememberMeManager.setCookie(simpleCookie);
        //对cookie进行加密操作【使用Base64进行加密操作】
        cookieRememberMeManager.setCipherKey(Base64.decode("6ZmI6I2j3Y+R1aSn5BOlAA=="));
        return cookieRememberMeManager;
    }




}

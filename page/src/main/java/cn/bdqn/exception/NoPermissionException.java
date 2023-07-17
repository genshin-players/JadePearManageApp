package cn.bdqn.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NoPermissionException {

    /*@ExceptionHandler(UnauthorizedException.class)
    public String handleShiroException(Exception ex) {
        return "index/error404";
    }

    @ExceptionHandler(AuthorizationException.class)
    public String AuthorizationException(Exception ex) {
        return "index/error404";
    }*/
}

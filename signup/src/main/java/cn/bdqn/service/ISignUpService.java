package cn.bdqn.service;

import cn.bdqn.entity.SignUp;
import cn.bdqn.mapper.SignUpMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 报名 服务类
 * </p>
 *
 * @author dddqmmx
 * @since 2023-06-09
 */
public interface ISignUpService extends IService<SignUp> {
    int signUp(SignUp signUp);


}

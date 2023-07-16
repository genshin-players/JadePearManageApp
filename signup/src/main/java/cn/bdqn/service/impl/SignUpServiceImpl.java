package cn.bdqn.service.impl;

import cn.bdqn.client.ActivatesClient;
import cn.bdqn.dto.ActivitiesDTO;
import cn.bdqn.entity.SignUp;
import cn.bdqn.mapper.SignUpMapper;
import cn.bdqn.service.ISignUpService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 报名 服务实现类
 * </p>
 *
 * @author dddqmmx
 * @since 2023-06-09
 */
@Service
public class SignUpServiceImpl extends ServiceImpl<SignUpMapper, SignUp> implements ISignUpService {
    @Autowired
    private SignUpMapper signUpMapper;
    @Autowired
    private ActivatesClient activatesClient;

    @Override
    public int signUp(SignUp signUp) {
        int activityId = signUp.getActivityId();
        ActivitiesDTO activitiesById = activatesClient.getActivitiesById(activityId);
        Integer signupNumber = activitiesById.getSignupNumber();
        LambdaQueryWrapper<SignUp> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(SignUp::getActivityId,activityId);
        List<SignUp> signUpList = signUpMapper.selectList(lambdaQueryWrapper);
        if (signUpList.size()>= signupNumber){
            return -1;
        }
        for (SignUp signUp1 : signUpList){
            if (signUp1.getSignupStudentId().equals(signUp.getSignupStudentId())){
                return -2;
            }
        }
        return signUpMapper.insert(signUp)>0?1:0;
    }
}

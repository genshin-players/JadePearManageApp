package cn.bdqn.controller;

import cn.bdqn.entity.SignUp;
import cn.bdqn.service.ISignUpService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/signUp")
@RestController
public class SignUpController {
    @Autowired
    private ISignUpService signUpService;

    @RequestMapping("/signUp")
    public Map<String,Object> signUp(SignUp signUp) {
        int code = signUpService.signUp(signUp);
        Map<String,Object> result = new HashMap<>();
        if (code == -1){
            result.put("massage","报名人数已满");
        }else if (code == -2){
            result.put("massage","你已报名完成");
        }
        result.put("code",code);
        return result;
    }

    @RequestMapping("/signUpListByActivityId")
    public List<SignUp> signUpListByActivityId(Integer activityId){
        LambdaQueryWrapper<SignUp> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SignUp::getActivityId,activityId);
        return signUpService.list(lambdaQueryWrapper);
    }
}
package cn.bdqn.service;

import cn.bdqn.entity.Activities;
import cn.bdqn.entity.Display;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 显示内容 服务类
 * </p>
 *
 * @author dddqmmx
 * @since 2023-06-09
 */
public interface IDisplayService extends IService<Display> {

    void list(LambdaQueryWrapper<Activities> lambdaQueryWrapper);
}

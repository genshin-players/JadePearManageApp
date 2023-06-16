package cn.bdqn.service.impl;

import cn.bdqn.entity.Display;
import cn.bdqn.mapper.DisplayMapper;
import cn.bdqn.service.IDisplayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 显示内容 服务实现类
 * </p>
 *
 * @author dddqmmx
 * @since 2023-06-09
 */
@Service
public class DisplayServiceImpl extends ServiceImpl<DisplayMapper, Display> implements IDisplayService {

}

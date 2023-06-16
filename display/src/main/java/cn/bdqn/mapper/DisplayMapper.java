package cn.bdqn.mapper;

import cn.bdqn.entity.Display;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 显示内容 Mapper 接口
 * </p>
 *
 * @author dddqmmx
 * @since 2023-06-09
 */
public interface DisplayMapper extends BaseMapper<Display> {
}

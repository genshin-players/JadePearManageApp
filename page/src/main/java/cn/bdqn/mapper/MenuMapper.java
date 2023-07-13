package cn.bdqn.mapper;

import cn.bdqn.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2023-07-11
 */
public interface MenuMapper extends BaseMapper<Menu> {


    List<Menu> getMenuByUserId(Integer userId);

}

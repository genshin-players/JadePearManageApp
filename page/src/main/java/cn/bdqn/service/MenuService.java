package cn.bdqn.service;

import cn.bdqn.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 * @author 
 * @since 2023-07-11
 */
public interface MenuService extends IService<Menu> {

    List<Menu> getMenuByUserId(Integer userId);
}

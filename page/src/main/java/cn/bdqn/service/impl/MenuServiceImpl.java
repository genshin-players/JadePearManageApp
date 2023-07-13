package cn.bdqn.service.impl;

import cn.bdqn.entity.Menu;
import cn.bdqn.mapper.MenuMapper;
import cn.bdqn.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author 
 * @since 2023-07-11
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuByUserId(Integer userId) {
        return menuMapper.getMenuByUserId(userId);
    }
}

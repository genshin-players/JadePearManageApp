package cn.bdqn.service;

import cn.bdqn.entity.Classes;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 班级 服务类
 * </p>
 *
 * @author 
 * @since 2023-06-15
 */
public interface ClassesService extends IService<Classes> {

    public List<Classes> getAllClasses();

}
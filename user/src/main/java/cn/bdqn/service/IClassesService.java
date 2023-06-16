package cn.bdqn.service;


import cn.bdqn.entity.Classes;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 班级 服务类
 * </p>
 *
 * @author pb
 * @since 2023-06-15
 */
public interface IClassesService extends IService<Classes> {

    Classes selectClassById(Integer id);

}

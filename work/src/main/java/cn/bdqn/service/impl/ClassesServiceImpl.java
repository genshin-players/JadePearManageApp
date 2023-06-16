package cn.bdqn.service.impl;

import cn.bdqn.entity.Classes;
import cn.bdqn.mapper.ClassesMapper;
import cn.bdqn.service.ClassesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 班级 服务实现类
 * </p>
 *
 * @author 
 * @since 2023-06-15
 */
@Service
public class ClassesServiceImpl extends ServiceImpl<ClassesMapper, Classes> implements ClassesService {
    @Autowired
    ClassesMapper classesMapper;

    @Override
    public List<Classes> getAllClasses() {
        return classesMapper.selectList(null);
    }
}
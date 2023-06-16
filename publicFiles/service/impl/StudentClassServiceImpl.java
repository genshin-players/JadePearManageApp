package com.dddqmmx.service.impl;

import com.dddqmmx.entity.StudentClass;
import com.dddqmmx.mapper.StudentClassMapper;
import com.dddqmmx.service.IStudentClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生与班级绑定表 服务实现类
 * </p>
 *
 * @author dddqmmx
 * @since 2023-06-09
 */
@Service
public class StudentClassServiceImpl extends ServiceImpl<StudentClassMapper, StudentClass> implements IStudentClassService {

}

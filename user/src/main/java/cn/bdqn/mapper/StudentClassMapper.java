package cn.bdqn.mapper;

import cn.bdqn.entity.StudentClass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface StudentClassMapper extends BaseMapper<StudentClass> {

    StudentClass selectStudentClassById(Integer id);
}

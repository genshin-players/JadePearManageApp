package cn.bdqn.service.impl;


import cn.bdqn.entity.StudentClass;
import cn.bdqn.mapper.StudentClassMapper;
import cn.bdqn.service.IStudentClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生与班级绑定表 服务实现类
 * </p>
 *
 * @author pb
 * @since 2023-06-15
 */
@Service
public class StudentClassServiceImpl extends ServiceImpl<StudentClassMapper, StudentClass> implements IStudentClassService {

   /* @Autowired
    private  StudentClassMapper studentClassMapper;
    @Override
    public StudentClass selectStudentClassById(int id) {
        StudentClass studentClass = studentClassMapper.selectById(id);
        System.out.println("看是否查询出值来"+studentClass.toString());
        return studentClass;
    }*/
}

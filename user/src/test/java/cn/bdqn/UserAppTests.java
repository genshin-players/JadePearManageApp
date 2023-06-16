package cn.bdqn;

import cn.bdqn.entity.StudentClass;
import cn.bdqn.mapper.StudentClassMapper;
import cn.bdqn.service.IClassesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserAppTests {

    @Autowired
    private StudentClassMapper studentClassMapper;
    @Test
    void contextLoads() {
        StudentClass studentClass=new StudentClass();
        studentClass.setClassId(1);
        studentClass.setStudentId(20);
       studentClassMapper.insert(studentClass);
    }

}

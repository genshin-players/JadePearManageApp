package cn.bdqn;

import cn.bdqn.entity.StudentClass;
import cn.bdqn.entity.Users;
import cn.bdqn.mapper.ClassesMapper;
import cn.bdqn.mapper.StudentClassMapper;
import cn.bdqn.service.IClassesService;
import cn.bdqn.service.IStudentClassService;
import cn.bdqn.service.IUsersService;
import cn.bdqn.service.impl.StudentClassServiceImpl;
import com.netflix.discovery.converters.Auto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserAppTests {

    @Autowired
    private IStudentClassService studentClassService;

    @Autowired
    private IUsersService usersService;
    @Test
    void contextLoads() {
       /* StudentClass studentClass=new StudentClass();
        studentClass.setClassId(1);
        studentClass.setStudentId(20);
        boolean save = studentClassService.save(studentClass);
        System.out.println(save);
*/

        List<Users> list = usersService.list();
        Users users2 = list.get(list.size() - 1);
        System.out.println(users2.getId());
    }

}

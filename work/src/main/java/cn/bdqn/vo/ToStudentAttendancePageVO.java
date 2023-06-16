package cn.bdqn.vo;

import cn.bdqn.entity.Class;
import cn.bdqn.entity.Classes;
import cn.bdqn.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 去往添加学生出勤页面所需要的数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToStudentAttendancePageVO {
    private List<Classes> classesList;//所有班级的列表
    private List<Users> userList; //所有用户的列表
    private Map<Integer,Object> StudentByClass;//学生按班级分
    private List<Class> student_class;//学生班级对应
}

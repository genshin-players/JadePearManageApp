package cn.bdqn.vo;

import cn.bdqn.entity.Attendence;
import cn.bdqn.entity.Classes;
import cn.bdqn.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 班级出勤详情页面信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassAttendanceDetailInfoVO {
    private Classes classes;//班级信息
    private Users student;//学生信息
    private Users reportUser;//报告人信息
    private Attendence attendence;//出勤记录
    private Users adviser;//班主任
}

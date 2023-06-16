package cn.bdqn.vo.workvo;

import cn.bdqn.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 学社出勤卡片点进去的数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberWorkDetailInfoVO {
    private Users member;//学社成员
    private Users adviser;//班主任
    private Users createUser;//排版人
    private Classes classes;//所属班级
    private Schedules schedules;//该成员指定日期的所有工作记录
    private SchedulesType schedulesType;//该学生指定的工作类型
    private List<WorkClass> workClasses;//负责班级

}

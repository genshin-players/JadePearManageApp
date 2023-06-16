package cn.bdqn.vo;

import cn.bdqn.entity.Classes;
import cn.bdqn.entity.Schedules;
import cn.bdqn.entity.SchedulesType;
import cn.bdqn.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学社工作卡片展示页面上展示的数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberWorkCardInfoVO {
    private Users member;//学社成员
    private Schedules schedules;//工作信息表
    private SchedulesType schedulesType;//工作分类信息
    private Classes classes;//班级表
    private Users createUser;//创建人，布置人
}

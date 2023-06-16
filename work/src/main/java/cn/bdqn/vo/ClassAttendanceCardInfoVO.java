package cn.bdqn.vo;

import cn.bdqn.entity.Classes;
import cn.bdqn.entity.Users;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 班级出勤页面每个卡片展示的数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassAttendanceCardInfoVO {

    private Classes classes;//班级信息对象
    private Long classSize;//班级人数
    private Long attendanceNumber;//出勤人数
    private Long  absenteesNumber;//缺勤人数
    private Long latecomersNumber;//迟到人数
    private Long leaverNumber;//请假人数
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date attendanceDate;//出勤日期
    private Users reportUser;//报告者对象

}

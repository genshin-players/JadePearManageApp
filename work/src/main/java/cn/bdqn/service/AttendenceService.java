package cn.bdqn.service;

import cn.bdqn.entity.Attendence;
import cn.bdqn.vo.ClassAttendanceCardInfoVO;
import cn.bdqn.vo.ClassAttendanceDetailInfoVO;
import cn.bdqn.vo.ToStudentAttendancePageVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 出勤 服务类
 * </p>
 *
 * @author 
 * @since 2023-06-09
 */
public interface AttendenceService extends IService<Attendence> {

    /**
     *  班级出勤页面卡片展示 按班级和出勤日期展示数据
     * @return map集合包含
     */
    public List<ClassAttendanceCardInfoVO> getClassAttendanceInfo(String attendanceDate);


    /**
     * 班级出勤详情页面展示的信息
     * @param attendanceDate 出勤日期
     * @param classId 班级编号
     * @return 信息集合
     */
    public List<ClassAttendanceDetailInfoVO> getClassAttendanceDetailInfo(String attendanceDate, Integer classId);

    /**
     * 去往添加学生出勤记录的页面 需要的数据
     * @return
     */
    public ToStudentAttendancePageVO ToStudentAttendancePage();


    /**
     * 添加学生出勤记录
     * @param attendence 出勤记录
     * @return 受影响的行数
     */
    public int addStudentAttendance(Attendence attendence);


    /**
     * 按学生ID和日期获取出勤记录
     * @param stuId 学生编号
     * @param attendanceDate 出勤日期
     * @return 出勤记录集合
     */
    public List<Attendence> getAttendanceByStuIdAndDate(Integer stuId,String attendanceDate);


    /**
     * 修改出勤记录
     * @param attendence 出勤记录及id
     * @return 受影响行数
     */
    public int updateStudentAttendance(Attendence attendence);

    /**
     * 按id删除出勤记录
     * @param attendanceId 出勤记录id
     * @return 受影响的行数
     */
    public int delStudentAttendance(Integer attendanceId);


    /**
     * 从小程序接收数据 添加到出勤表
     * @return
     */
    public int addClassAttendce(Integer classId,Date date,Integer reportUserId,Integer schedulesId,
            Integer[]resQ,Integer[]resC,Integer[]resQj);

    /**
     * 从小程序接收数据 添加到出勤表 全部到齐
     * @return
     */
    public int addAllClassAttendce(Integer classId,Date date,Integer reportUserId,Integer schedulesId);

}

package cn.bdqn.client;

import cn.bdqn.entity.Attendence;
import cn.bdqn.entity.Classes;
import cn.bdqn.entity.Schedules;
import cn.bdqn.entity.SchedulesType;
import cn.bdqn.vo.workvo.*;
import cn.bdqn.vo.ResultVO;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name="work")
public interface WorkClient {

    @RequestMapping("/classAttendance.html")
    public ResultVO<List<ClassAttendanceCardInfoVO>> classAttendanceCardList(@RequestParam("attendanceDate") String attendanceDate);

    @RequestMapping("/classAttendanceDetail.html")
    public ResultVO<List<ClassAttendanceDetailInfoVO>> classAttendanceDetail(@RequestParam("attendanceDate")String attendanceDate, @RequestParam("classId") Integer classId);

    /**
     * 去添加学生出勤页面所需要的数据
     * @return 数据
     */
    @RequestMapping("/toStudentAttendancePage")
    public ResultVO<ToStudentAttendancePageVO> toStudentAttendancePageVO();


    @PostMapping("/addStudentAttendance")
    public ResultVO<Integer> addStudentAttendance(@RequestBody Attendence attendence);

    @PostMapping("/updateStudentAttendance")
    public ResultVO<Integer> updateStudentAttendance(@RequestBody Attendence attendence);


    //根据学生id和日期获取 出勤记录
    @RequestMapping("/getAttendanceByStuIdAndDate")
    public ResultVO<List<Attendence>> getAttendanceByStuIdAndDate(@RequestParam("stuId") Integer stuId,@RequestParam("attendanceDate") String attendanceDate);


    //根据出勤id获取出勤记录
    @RequestMapping("/getAttendanceById")
    public ResultVO<Attendence> getAttendanceById(@RequestParam("attendanceId") Integer attendanceId);

    //根据id删除出勤记录
    @PostMapping("/delStudentAttendance")
    public ResultVO<Integer> delStudentAttendance(@RequestParam("attendanceId") Integer attendanceId);





    //学社出勤页面 卡片数据
    @GetMapping("/toMemberWorkCardInfo")
    public ResultVO<List<MemberWorkCardInfoVO>> getMemberWorkCardInfo(@RequestParam("workDate")String workDate);

    //学社成员出勤详情
    @GetMapping("/toMemberWorkDetailInfo")
    public ResultVO<List<MemberWorkDetailInfoVO>> getMemberWorkDetailInfo(@RequestParam("memberId") Integer memberId);



    //获取所有班级
    @RequestMapping("/getAllClasses")
    public ResultVO<List<Classes>> getAllClasses();

    @RequestMapping("/getAllWorkType")
    public List<SchedulesType> typeList();


    @PostMapping("/assignOneWork")
    public ResultVO<Integer>assignOneWork(@RequestBody Schedules schedules,@RequestParam(value = "classIdArray",required = false) Integer[]classIdArray);




    @PostMapping("/getSchedulesById")
    public Schedules getSchedulesById(@RequestParam("schedulesId") Integer schedulesId);


    @PostMapping("/updateOneWork")
    public ResultVO<Integer> updateOneWork(@RequestBody Schedules schedules,@RequestParam(value = "classIdArray",required = false) Integer[]classIdArray);


    @PostMapping("/deleteOneWork")
    public ResultVO<Integer> deleteOneWork(@RequestParam("schedulesId") Integer schedulesId);


    //给指定成员安排工作请求
    @ResponseBody
    @PostMapping("/assignMoreWork")
    public ResultVO<Integer> assignMoreWork(@RequestBody Schedules schedules,
                                            @RequestParam(value = "memberIdArray[]") Integer[]memberIdArray,
                                            @RequestParam(value = "classIdArray[]",required = false) Integer[]classIdArray);

}

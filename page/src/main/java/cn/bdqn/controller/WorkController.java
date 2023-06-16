package cn.bdqn.controller;

import cn.bdqn.client.WorkClient;
import cn.bdqn.entity.Attendence;
import cn.bdqn.entity.Users;
import cn.bdqn.vo.workvo.*;
import cn.bdqn.vo.ResultVO;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.errorprone.annotations.FormatString;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@CrossOrigin
public class WorkController {
    @Autowired
    WorkClient workClient;

    //学生出勤页面请求
    @RequestMapping("/toStuAttendance")
    public String toStuAttendance(@RequestParam(required = false) String attendanceDate,Model model){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        if(attendanceDate==null){
            attendanceDate=sdf.format(new Date());
        }else {
            try {
                Date date=sdf.parse(attendanceDate);
                attendanceDate=sdf.format(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        ResultVO<List<ClassAttendanceCardInfoVO>> resultVO = workClient.classAttendanceCardList(attendanceDate);
        model.addAttribute("resultVO",resultVO);
        model.addAttribute("pageDate",attendanceDate);
        return "work/studentAttendance";
    }


    //学生出勤详情页面请求
    @RequestMapping("/toStuAttendanceDetail")
    public String toStuAttendanceDetail(String attendanceDate,Integer classId,Model model) {

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        if(attendanceDate==null){
            attendanceDate=sdf.format(new Date());
        }else{
            try {
                Date date=sdf.parse(attendanceDate);
                attendanceDate=sdf.format(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        ResultVO<List<ClassAttendanceDetailInfoVO>> resultVO=workClient.classAttendanceDetail(attendanceDate,classId);
        model.addAttribute("resultVO",resultVO);
        model.addAttribute("pageDate",attendanceDate);
        model.addAttribute("classes",resultVO.getData().get(0).getClasses());

        return "work/studentAttendanceDetail";
    }
    //学社出勤页面请求
    @RequestMapping("/toMembersAttendance")
    public String toMembersAttendance(){
        return "work/MembersAttendance";
    }
    //学社出勤详情页面请求
    @RequestMapping("/toMemAttendanceDetail")
    public String toMemAttendanceDetail(String memName,String date,Model model) throws ParseException {
        model.addAttribute("memName", memName);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date1=sdf.parse(date);
        model.addAttribute("date", date1);
        return "work/MembersAttendanceDetail";
    }

    //班级出勤页面去往  新增出勤记录页面
    @RequestMapping("/toAddStudentAttendance")
    public String toAddStudentAttendance(Model model){
        ResultVO<ToStudentAttendancePageVO> resultVO = workClient.toStudentAttendancePageVO();
        model.addAttribute("resultVO",resultVO);
        model.addAttribute("editType","新增");
        return "work/editStudentAttendance";
    }

    //班级出勤页面点击 修改  修改出勤记录
    @RequestMapping("/toUpdateStudentAttendance")
    public String toUpdateStudentAttendance(@RequestParam("attendanceId") Integer attendanceId,Model model){
        ResultVO<ToStudentAttendancePageVO> resultVO = workClient.toStudentAttendancePageVO();
        model.addAttribute("resultVO",resultVO);
        model.addAttribute("editType","修改");

        model.addAttribute("attendance",workClient.getAttendanceById(attendanceId));
        return "work/updateStudentAttendance";
    }

    //根据出勤id获取出勤记录
    @RequestMapping("/getAttendanceById")
    public ResultVO<Attendence> getAttendanceById(@RequestParam("attendanceId") Integer attendanceId){
        return workClient.getAttendanceById(attendanceId);
    }
    @ResponseBody
    @GetMapping("/getStudentByClass")
    public Map<String,Object> getStudentByClass(Integer classId){
        Map<String,Object>map=new HashMap<>();
        ResultVO<ToStudentAttendancePageVO> resultVO = workClient.toStudentAttendancePageVO();
        List<Users> classList=new ArrayList<>();
        if(classId!=0){
            Map<Integer, Object> studentByClass = resultVO.getData().getStudentByClass();
            classList= (List<Users>) studentByClass.get(classId);
        }else {
            List<Users> userList=resultVO.getData().getUserList();
            for (Users u:userList){
                if(u.getRoleId()==6||u.getRoleId()==5){
                    classList.add(u);
                }
            }
        }
        map.put("studentByClass",classList);
        map.put("studentAndClass",resultVO.getData().getStudent_class());
        map.put("classList",resultVO.getData().getClassesList());
        return map;
    }

    //新增出勤记录
    @PostMapping("/addStudentAttendance")
    @ResponseBody
    public ResultVO<Integer> addStudentAttendance(Attendence attendence,String attendanceDate){
        Date date=null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            date=sdf.parse(attendanceDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        attendence.setDate(date);
        ResultVO<Integer> resultVO=workClient.addStudentAttendance(attendence);
        return resultVO;
    }

    @PostMapping("/updateStudentAttendance")
    @ResponseBody
    public ResultVO<Integer> updateStudentAttendance(Attendence attendence,String attendanceDate){
        Date date=null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            date=sdf.parse(attendanceDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        attendence.setDate(date);
        ResultVO<Integer> resultVO=workClient.updateStudentAttendance(attendence);
        return resultVO;
    }

    @PostMapping("/delStudentAttendance")
    @ResponseBody
    public ResultVO<Integer> delStudentAttendance(@RequestParam("attendanceId") Integer attendanceId){
        return workClient.delStudentAttendance(attendanceId);
    }

    //根据学生和日期获取出勤记录
    @PostMapping("/getAttendanceByStuIdAndDate")
    @ResponseBody
    public ResultVO<List<Attendence>> getAttendanceByStuIdAndDate(Integer stuId,String attendanceDate){
        return workClient.getAttendanceByStuIdAndDate(stuId,attendanceDate);
    }

    //班级出勤编辑页面请求
    @RequestMapping("/toEditStudentAttendance")
    public String toEditStudentAttendance(){
        return "work/editStudentAttendance";
    }
    //安排工作页面
    @RequestMapping("/toAssignWork")
    public String toAssignOneWork(@RequestParam(required = false) String memName, Model model){
        if(memName!=null){
            model.addAttribute("memName", memName);
        }else {
            model.addAttribute("memName", "sb");
        }
        return "work/assignWork";
    }








    //学社工作页面请求
    @RequestMapping("/toMemberWork")
    public String toMemberWork(@RequestParam(required = false) String workDate,Model model){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        if(workDate==null){
            workDate=sdf.format(new Date());
        }else {
            try {
                Date date=sdf.parse(workDate);
                workDate=sdf.format(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        ResultVO<List<MemberWorkCardInfoVO>> resultVO = workClient.getMemberWorkCardInfo(workDate);
        model.addAttribute("resultVO",resultVO);
        model.addAttribute("pageDate",workDate);
        //System.out.println(resultVO.getData().get(0).getCreateUser().toString());
        return "work/MembersAttendance";
    }

    //学社工作页面请求
    @RequestMapping("/toMemberWorkDetailInfo")
    public String getMemberWorkDetailInfo(Integer memberId,Model model){

        ResultVO<List<MemberWorkDetailInfoVO>> resultVO=workClient.getMemberWorkDetailInfo(memberId);
        model.addAttribute("resultVO",resultVO);

        model.addAttribute("member",resultVO.getData().get(0).getMember());
        model.addAttribute("adviser",resultVO.getData().get(0).getAdviser());
        model.addAttribute("classes",resultVO.getData().get(0).getClasses());
        return "work/MembersAttendanceDetail";
    }
}

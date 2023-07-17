package cn.bdqn.controller;

import cn.bdqn.client.UserClient;
import cn.bdqn.client.WorkClient;
import cn.bdqn.entity.Attendence;
import cn.bdqn.entity.Schedules;
import cn.bdqn.entity.Users;
import cn.bdqn.vo.workvo.*;
import cn.bdqn.vo.ResultVO;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.errorprone.annotations.FormatString;
import lombok.Data;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
//@RequestMapping("/work")
public class WorkController {
    @Autowired
    WorkClient workClient;
    @Autowired
    UserClient userClient;

    //学生出勤页面请求
    @RequestMapping("/stuAttendance")
    @RequiresPermissions("/work/stuAttendance")
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
    @RequestMapping("/stuAttendance/toStuAttendanceDetail")
    @RequiresPermissions("/work/stuAttendance")
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




    //班级出勤页面去往  新增出勤记录页面
    @RequestMapping("/stuAttendance/toAddStudentAttendance")
    @RequiresPermissions("/work/stuAttendance")
    public String toAddStudentAttendance(Model model){
        ResultVO<ToStudentAttendancePageVO> resultVO = workClient.toStudentAttendancePageVO();
        model.addAttribute("resultVO",resultVO);
        model.addAttribute("editType","新增");
        return "work/editStudentAttendance";
    }

    //班级出勤页面点击 修改  修改出勤记录
    @RequestMapping("/stuAttendance/toUpdateStudentAttendance")
    @RequiresPermissions("/work/stuAttendance")
    public String toUpdateStudentAttendance(@RequestParam("attendanceId") Integer attendanceId,Model model){
        ResultVO<ToStudentAttendancePageVO> resultVO = workClient.toStudentAttendancePageVO();
        model.addAttribute("resultVO",resultVO);
        model.addAttribute("editType","修改");

        model.addAttribute("attendance",workClient.getAttendanceById(attendanceId));
        return "work/editStudentAttendance";
    }

    //根据出勤id获取出勤记录
    @RequestMapping("/stuAttendance/getAttendanceById")
    @RequiresPermissions("/work/stuAttendance")
    public ResultVO<Attendence> getAttendanceById(@RequestParam("attendanceId") Integer attendanceId){
        return workClient.getAttendanceById(attendanceId);
    }


    @ResponseBody
    @GetMapping("/stuAttendance/getStudentByClass")
    @RequiresPermissions("/work/stuAttendance")
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
    @PostMapping("/stuAttendance/addStudentAttendance")
    @ResponseBody
    @RequiresPermissions("/work/stuAttendance")
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

    @PostMapping("/stuAttendance/updateStudentAttendance")
    @ResponseBody
    @RequiresPermissions("/work/stuAttendance")
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

    @PostMapping("/stuAttendance/delStudentAttendance")
    @ResponseBody
    @RequiresPermissions("/work/stuAttendance")
    public ResultVO<Integer> delStudentAttendance(@RequestParam("attendanceId") Integer attendanceId){
        return workClient.delStudentAttendance(attendanceId);
    }

    //根据学生和日期获取出勤记录
    @PostMapping("/stuAttendance/getAttendanceByStuIdAndDate")
    @ResponseBody
    @RequiresPermissions("/work/stuAttendance")
    public ResultVO<List<Attendence>> getAttendanceByStuIdAndDate(Integer stuId,String attendanceDate){
        return workClient.getAttendanceByStuIdAndDate(stuId,attendanceDate);
    }





    //安排工作页面
    @RequestMapping("/assignWork")
    @RequiresPermissions("/work/assignWork")
    public String toAssignOneWork(@RequestParam(required = false) Integer memId, Model model){
        if(memId!=null){
            Users users= userClient.getUserById(memId);
            model.addAttribute("member",users);
            model.addAttribute("classesList",workClient.getAllClasses());
            model.addAttribute("workTypeList",workClient.typeList());
        }else {

            model.addAttribute("memberList",userClient.getAllMember());
            model.addAttribute("member", "sb");
            model.addAttribute("classesList",workClient.getAllClasses());
            model.addAttribute("workTypeList",workClient.typeList());
        }
        return "work/assignWork";
    }

    //安排工作页面
    @RequestMapping("/assignWork/toAssignMoreWork")
    @RequiresPermissions("/work/assignWork")
    public String toAssignMoreWork(@RequestParam(required = false) Integer memId, Model model){
        if(memId!=null){
            Users users= userClient.getUserById(memId);
            model.addAttribute("member",users);
            model.addAttribute("classesList",workClient.getAllClasses());
            model.addAttribute("workTypeList",workClient.typeList());
        }else {

            model.addAttribute("memberList",userClient.getAllMember());
            model.addAttribute("member", "sb");
            model.addAttribute("classesList",workClient.getAllClasses());
            model.addAttribute("workTypeList",workClient.typeList());
        }
        return "work/assignMoreWork";
    }


    //给指定成员安排工作请求
    @ResponseBody
    @PostMapping("/assignWork/assignOneWork")
    @RequiresPermissions("/work/assignWork")
    public ResultVO<Integer> assignOneWork(Schedules schedules,@RequestParam(value = "classIdArray[]",required = false) Integer[]classIdArray){
        ResultVO<Integer> mapResultVO = workClient.assignOneWork(schedules, classIdArray);
        return mapResultVO;
    }


    //给指定成员安排工作请求
    @ResponseBody
    @PostMapping("/assignWork/assignMoreWork")
    @RequiresPermissions("/work/assignWork")
    public ResultVO<Integer> assignMoreWork(Schedules schedules,
                                            @RequestParam(value = "memberIdArray[]") Integer[]memberIdArray,
                                            @RequestParam(value = "classIdArray[]",required = false) Integer[]classIdArray){
        ResultVO<Integer> mapResultVO = workClient.assignMoreWork(schedules, memberIdArray,classIdArray);
        return mapResultVO;
    }



    //给指定成员安排工作请求
    @ResponseBody
    @PostMapping("/memberWork/updateOneWork")
    @RequiresPermissions("/work/memberWork")
    public ResultVO<Integer> updateOneWork(Schedules schedules,@RequestParam(value = "classIdArray[]",required = false) Integer[]classIdArray){
        ResultVO<Integer> mapResultVO = workClient.updateOneWork(schedules, classIdArray);
        return mapResultVO;
    }

    //给指定成员安排工作请求
    @ResponseBody
    @PostMapping("/memberWork/deleteOneWork")
    @RequiresPermissions("/work/memberWork")
    public ResultVO<Integer> deleteOneWork(@RequestParam("schedulesId") Integer schedulesId){
        ResultVO<Integer> mapResultVO = workClient.deleteOneWork(schedulesId);
        return mapResultVO;
    }


    //班级出勤编辑页面请求
    @RequestMapping("/memberWork/toUpdateMemberWork")
    @RequiresPermissions("/work/memberWork")
    public String toEditStudentAttendance(Integer schedulesId,Model model){
        Schedules schedules1=workClient.getSchedulesById(schedulesId);
        Users member=userClient.getUserById(schedules1.getMemberId());
        model.addAttribute("member",member);
        model.addAttribute("workTypeList",workClient.typeList());
        model.addAttribute("classesList",workClient.getAllClasses());
        model.addAttribute("schedules",schedules1);

        return "work/updateWork";
    }





    //学社工作页面请求
    @RequestMapping("/memberWork")
    @RequiresPermissions("/work/memberWork")
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
        model.addAttribute("classesList",workClient.getAllClasses());
        //System.out.println(resultVO.getData().get(0).getCreateUser().toString());
        return "work/MembersAttendance";
    }

    //学社工作页面请求
    @RequestMapping("/memberWork/toMemberWorkDetailInfo")
    @RequiresPermissions("/work/memberWork")
    public String getMemberWorkDetailInfo(Integer memberId,Model model){

        ResultVO<List<MemberWorkDetailInfoVO>> resultVO=workClient.getMemberWorkDetailInfo(memberId);
        model.addAttribute("resultVO",resultVO);

        model.addAttribute("member",resultVO.getData().get(0).getMember());
        model.addAttribute("adviser",resultVO.getData().get(0).getAdviser());
        model.addAttribute("classes",resultVO.getData().get(0).getClasses());
        model.addAttribute("classesList",workClient.getAllClasses());
        return "work/MembersAttendanceDetail";
    }
}

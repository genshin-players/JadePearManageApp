package cn.bdqn.controller;


import cn.bdqn.entity.Attendence;
import cn.bdqn.entity.Classes;
import cn.bdqn.entity.SchedulesType;
import cn.bdqn.service.AttendenceService;
import cn.bdqn.service.ClassesService;
import cn.bdqn.service.SchedulestypeService;
import cn.bdqn.vo.ClassAttendanceCardInfoVO;
import cn.bdqn.vo.ClassAttendanceDetailInfoVO;
import cn.bdqn.vo.ToStudentAttendancePageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AttendanceController {
    @Autowired
    AttendenceService attendenceService;
    @Autowired
    ClassesService classesService;
    @Autowired
    SchedulestypeService schedulestypeService;


    //获取所有班级类型
    @RequestMapping("/getAllWorkType")
    public List<SchedulesType> typeList(){
        return schedulestypeService.list();
    }

    //获取所有班级
    @RequestMapping("/getAllClasses")
    public Map<String,Object> getAllClasses(){
        Map<String,Object>map=new HashMap<>();
        try{
            List<Classes> classesList=classesService.getAllClasses();
            map.put("data",classesList);
            map.put("msg","success");
            map.put("code","200");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","error");
            map.put("code","500");
        }
        return map;
    }



    @RequestMapping("/classAttendance.html")
    public Map<String,Object> classAttendanceCardList(String attendanceDate){
        Map<String,Object>map=new HashMap<>();
        try{
            List<ClassAttendanceCardInfoVO> classAttendanceInfo = attendenceService.getClassAttendanceInfo(attendanceDate);
            map.put("data",classAttendanceInfo);
            map.put("msg","success");
            map.put("code","200");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","error");
            map.put("code","500");
        }
        return map;
    }


    @RequestMapping("/classAttendanceDetail.html")
    public Map<String,Object> classAttendanceDetail(String attendanceDate, Integer classId){
        Map<String,Object>map=new HashMap<>();
        try{
            List<ClassAttendanceDetailInfoVO> classAttendanceDetailInfo=attendenceService.getClassAttendanceDetailInfo(attendanceDate, classId);
            map.put("data",classAttendanceDetailInfo);
            map.put("msg","success");
            map.put("code","200");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","error");
            map.put("code","500");
        }
        return map;
    }


    /**
     * 去添加学生出勤页面所需要的数据
     * @return 数据
     */
    @RequestMapping("/toStudentAttendancePage")
    public Map<String,Object> toStudentAttendancePageVO(){
        Map<String,Object>map=new HashMap<>();
        try{
            ToStudentAttendancePageVO vo=attendenceService.ToStudentAttendancePage();
            map.put("data",vo);
            map.put("msg","success");
            map.put("code","200");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","error");
            map.put("code","500");
        }
        return map;
    }


    @PostMapping("/addStudentAttendance")
    public Map<String,Object> addStudentAttendance(@RequestBody Attendence attendence){
        Map<String,Object>map=new HashMap<>();
        try{
            int i = attendenceService.addStudentAttendance(attendence);
            if(i>0){
                map.put("data",i);
                map.put("msg","success");
                map.put("code","200");
            }else {
                map.put("msg","error");
                map.put("code","500");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","error");
            map.put("code","500");
        }
        return map;
    }


    @RequestMapping("/getAttendanceByStuIdAndDate")
    public Map<String,Object> getAttendanceByStuIdAndDate(Integer stuId, String attendanceDate){
        Map<String,Object>map=new HashMap<>();
        try{
            List<Attendence>  attendences= attendenceService.getAttendanceByStuIdAndDate(stuId,attendanceDate);

                map.put("data",attendences);
                map.put("msg","success");
                map.put("code","200");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","error");
            map.put("code","500");
        }
        return map;
    }

    @RequestMapping("/getAttendanceById")
    public Map<String,Object> getAttendanceById(Integer attendanceId){
        Map<String,Object>map=new HashMap<>();
        try{
            Attendence attendence=attendenceService.getById(attendanceId);

            map.put("data",attendence);
            map.put("msg","success");
            map.put("code","200");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","error");
            map.put("code","500");
        }
        return map;
    }


    @PostMapping("/updateStudentAttendance")
    public Map<String,Object> updateStudentAttendance(@RequestBody Attendence attendence){
        Map<String,Object>map=new HashMap<>();
        try{
            int i = attendenceService.updateStudentAttendance(attendence);
            if(i>0){
                map.put("data",i);
                map.put("msg","success");
                map.put("code","200");
            }else {
                map.put("msg","error");
                map.put("code","500");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","error");
            map.put("code","500");
        }
        return map;
    }

    @PostMapping("/delStudentAttendance")
    public Map<String,Object> delStudentAttendance(Integer attendanceId){
        Map<String,Object>map=new HashMap<>();
        try{
            int i = attendenceService.delStudentAttendance(attendanceId);
            if(i>0){
                map.put("data",i);
                map.put("msg","success");
                map.put("code","200");
            }else {
                map.put("msg","error");
                map.put("code","500");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","error");
            map.put("code","500");
        }
        return map;
    }

}

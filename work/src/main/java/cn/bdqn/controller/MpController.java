package cn.bdqn.controller;

import cn.bdqn.entity.Classes;
import cn.bdqn.service.AttendenceService;
import cn.bdqn.service.ClassesService;
import cn.bdqn.service.SchedulesService;
import cn.bdqn.vo.ClassAttendanceCardInfoVO;
import cn.bdqn.vo.MemberWorkCardInfoVO;
import cn.bdqn.vo.MemberWorkClassVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 提供给小程序的接口
 */
@CrossOrigin
@RestController
@RequestMapping("/mp-work")
public class MpController {
    @Autowired
    AttendenceService attendenceService;
    @Autowired
    SchedulesService schedulesService;
    @Autowired
    ClassesService classesService;


    @PostMapping("/classAttendance")
    public Map<String,Object> classAttendanceCardList(@RequestParam(required = false) String attendanceDate){

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


        Map<String,Object>map=new HashMap<>();
        try{
            List<ClassAttendanceCardInfoVO> classAttendanceInfo = attendenceService.getClassAttendanceInfo(attendanceDate);
            map.put("data",classAttendanceInfo);
            map.put("date",attendanceDate);
            map.put("msg","success");
            map.put("code","200");
        }catch (Exception e){
            e.printStackTrace();
            map.put("date",attendanceDate);
            map.put("msg","error");
            map.put("code","500");
        }
        return map;
    }




    @PostMapping("/getWorkById")
    public Map<String,Object> getMemberWorkById(Integer memberId){
        Map<String,Object> map=new HashMap<>();
        try {
            List<MemberWorkCardInfoVO> memberWork = schedulesService.getMemberWorkCardInfoById(memberId);
            map.put("data",memberWork);
            map.put("msg","success");
            map.put("code","200");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","error");
            map.put("code","500");
        }
        return map;
    }


    @PostMapping("/confirmWork")
    public Map<String,Object> confirmWork(Integer schedulesId){
        Map<String,Object> map=new HashMap<>();
        try {
            Integer count = schedulesService.confirmWork(schedulesId);
            map.put("data",count);
            map.put("msg","success");
            map.put("code","200");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","error");
            map.put("code","500");
        }
        return map;
    }


    @PostMapping("/getWorkClass")
    public Map<String,Object> getWorkClass(Integer schedulesId){
        Map<String,Object> map=new HashMap<>();
        try {
            MemberWorkClassVO workClass = schedulesService.getWorkClass(schedulesId);
            map.put("data",workClass);
            map.put("msg","success");
            map.put("code","200");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","error");
            map.put("code","500");
        }
        return map;
    }



    @PostMapping("/addClassAttendce")
    public Map<String,Object> addClassAttendce(Integer classId, String date, Integer reportUserId,Integer schedulesId,
                                               Integer[] resQ, Integer[] resC, Integer[] resQj){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date1=null;
        try {
            date1=sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Map<String,Object> map=new HashMap<>();
        try {
            int i = attendenceService.addClassAttendce(classId, date1, reportUserId, schedulesId,resQ, resC, resQj);
            map.put("data",i);
            map.put("msg","success");
            map.put("code","200");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","error");
            map.put("code","500");
        }
        return map;
    }


    @PostMapping("/addAllClassAttendce")
    public Map<String,Object> addAllClassAttendce(Integer classId, String date, Integer reportUserId,Integer schedulesId){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date1=null;
        try {
            date1=sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Map<String,Object> map=new HashMap<>();
        try {
            int i = attendenceService.addAllClassAttendce(classId, date1, reportUserId,schedulesId);
            map.put("data",i);
            map.put("msg","success");
            map.put("code","200");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","error");
            map.put("code","500");
        }
        return map;
    }
}

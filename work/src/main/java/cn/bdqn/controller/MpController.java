package cn.bdqn.controller;

import cn.bdqn.service.AttendenceService;
import cn.bdqn.vo.ClassAttendanceCardInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
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
}

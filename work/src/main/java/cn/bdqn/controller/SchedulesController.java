package cn.bdqn.controller;

import cn.bdqn.entity.Schedules;
import cn.bdqn.service.SchedulesService;
import cn.bdqn.vo.MemberWorkCardInfoVO;
import cn.bdqn.vo.MemberWorkDetailInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//
@RestController
public class SchedulesController {
    @Autowired
    SchedulesService schedulesService;


    @GetMapping("/toMemberWorkCardInfo")
    public Map<String,Object> getMemberWorkCardInfo(String workDate) {
        Map<String, Object> map = new HashMap<>();

        try {
            List<MemberWorkCardInfoVO> memberWorkCardInfo = schedulesService.getMemberWorkCardInfo(workDate);
            map.put("data", memberWorkCardInfo);
            map.put("msg", "success");
            map.put("code", "200");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "error");
            map.put("code", "500");
        }
        return map;
    }

    @GetMapping("/toMemberWorkDetailInfo")
    public Map<String, Object> getMemberWorkDetailInfo(Integer memberId){
        Map<String, Object> map = new HashMap<>();

        try {
            List<MemberWorkDetailInfoVO> memberWorkDetailInfo =schedulesService.getMemberWorkDetailInfo(memberId);
            map.put("data", memberWorkDetailInfo);
            map.put("msg", "success");
            map.put("code", "200");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "error");
            map.put("code", "500");
        }
        return map;
    }


    @PostMapping("/assignOneWork")
    public Map<String,Object>assignOneWork(@RequestBody Schedules schedules, @RequestParam(value = "classIdArray",required = false) Integer[]classIdArray){
        Map<String, Object> map = new HashMap<>();

        try {
            Integer count=schedulesService.assignOneWork(schedules,classIdArray);
            map.put("data", count);
            map.put("msg", "success");
            map.put("code", "200");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "error");
            map.put("code", "500");
        }
        return map;
    }

    //给指定成员安排工作请求
    @ResponseBody
    @PostMapping("/assignMoreWork")
    public Map<String,Object> assignMoreWork(@RequestBody Schedules schedules,
                                             @RequestParam(value = "memberIdArray[]") Integer[]memberIdArray,
                                             @RequestParam(value = "classIdArray[]",required = false) Integer[]classIdArray){
        Map<String, Object> map = new HashMap<>();

        try {
            Integer count=schedulesService.assignMoreWork(schedules,memberIdArray,classIdArray);
            map.put("data", count);
            map.put("msg", "success");
            map.put("code", "200");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "error");
            map.put("code", "500");
        }
        return map;

    }



    @PostMapping("/updateOneWork")
    public Map<String, Object> updateOneWork(@RequestBody Schedules schedules,@RequestParam(value = "classIdArray",required = false) Integer[]classIdArray){
        Map<String, Object> map = new HashMap<>();

        try {
            Integer count=schedulesService.updateOneWork(schedules,classIdArray);
            map.put("data", count);
            map.put("msg", "success");
            map.put("code", "200");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "error");
            map.put("code", "500");
        }
        return map;
    }

    @PostMapping("/deleteOneWork")
    public Map<String, Object> deleteOneWork(@RequestParam("schedulesId") Integer schedulesId){
        Map<String, Object> map = new HashMap<>();

        try {
            Integer count=schedulesService.deleteOneWork(schedulesId);
            map.put("data", count);
            map.put("msg", "success");
            map.put("code", "200");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "error");
            map.put("code", "500");
        }
        return map;
    }


    @PostMapping("/getSchedulesById")
    public Schedules getSchedulesById(Integer schedulesId){
        return schedulesService.getById(schedulesId);
    }






}
package cn.bdqn.controller;


import cn.bdqn.dto.ActivitiesDTO;
import cn.bdqn.entity.Activities;
import cn.bdqn.entity.Display;
import cn.bdqn.service.IActivitiesService;
import cn.bdqn.service.IDisplayService;
import cn.bdqn.util.DateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dddqmmx
 * @since 2023-06-09
 */
@RestController
@RequestMapping("/activities")
public class ActivitiesController {

    @Autowired
    private IActivitiesService activitiesService;
    @Autowired
    private IDisplayService displayService;

    @RequestMapping("/activitiesList")
    public List<ActivitiesDTO> getActivitiesList(){
        List<Activities> list = activitiesService.list();
        List<ActivitiesDTO> dtoList = new ArrayList<>();
        for (Activities activities:list){
            ActivitiesDTO activitiesDTO = new ActivitiesDTO();
            BeanUtils.copyProperties(activities, activitiesDTO);
            int displayId =activities.getDisplayId();
            Display display = displayService.getById(displayId);
            activitiesDTO.setDisplay(display);
            dtoList.add(activitiesDTO);
        }
        return dtoList;
    }


    @RequestMapping("/getActivitiesById")
    @ResponseBody
    public Map<String,Object> getActivitiesById(@RequestParam("id") Integer id){
        Activities activities = activitiesService.getById(id);
        ActivitiesDTO activitiesDTO = new ActivitiesDTO();
        BeanUtils.copyProperties(activities, activitiesDTO);
        int displayId =activities.getDisplayId();
        Display display = displayService.getById(displayId);
        activitiesDTO.setDisplay(display);
        Map<String,Object> map = new HashMap<>();
        if (activities!=null&&activitiesDTO!=null){
            map.put("code", 200);
            map.put("msg", "success");
            map.put("data",activitiesDTO);
        }else {
            map.put("code", 500);
            map.put("msg", "error");
        }
        return map;
    }

    @RequestMapping("/activitiesListByTitle")
    @ResponseBody
    public Map<String,Object> activitiesListByTitle(@RequestParam("title") String title){
        LambdaQueryWrapper<Display> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Display::getTitle, "%"+title+"%");
        List<Display> displayList = displayService.list(lambdaQueryWrapper);
        List<ActivitiesDTO> dtoList = new ArrayList<>();
        for (Display display : displayList){
            int displayId = display.getId();
            LambdaQueryWrapper<Activities> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
            lambdaQueryWrapper1.like(Activities::getDisplayId, displayId);
            List<Activities> activatedList = activitiesService.list(lambdaQueryWrapper1);
            ActivitiesDTO activitiesDTO = new ActivitiesDTO();
            BeanUtils.copyProperties(activatedList.get(0), activitiesDTO);
            activitiesDTO.setDisplay(display);
            dtoList.add(activitiesDTO);
        }
        Map<String,Object> map = new HashMap<>();
        if (displayList.size() > 0){
            map.put("code", 200);
            map.put("msg", "success");
            map.put("data", dtoList);
        }else {
            map.put("code", 500);
            map.put("msg", "error");
        }
        return map;
    }

    @RequestMapping("deleteActivitiesById")
    public Map<String, Object> deleteActivitiesById(@RequestParam(value = "activitiesId") Integer activitiesId,@RequestParam(value = "displayId") Integer displayId){
        boolean activitiesB = activitiesService.removeById(activitiesId);
        boolean displayB = displayService.removeById(displayId);
        Map<String,Object> map = new HashMap<>();
        if (activitiesB && displayB){
            map.put("code", 200);
            map.put("msg", "success");
        }else {
            map.put("code", 500);
            map.put("msg", "error");
        }
        return map;
    }

    @RequestMapping("addActivities")
    public Map<String, Object> addActivities(
            @RequestParam(value = "displayId") Integer displayId,
            @RequestParam(value = "signupNum") Integer signupNumber,
            @RequestParam(value = "startTime") String startTime,
            @RequestParam(value = "endTime") String endTime){
        Map<String,Object> map = new HashMap<>();
        try {
            if (activitiesService.save(
                    Activities.builder()
                            .displayId(displayId)
                            .signupNumber(signupNumber)
                            .startTime(DateTimeUtil.sdf.parse(startTime))
                            .endTime(DateTimeUtil.sdf.parse(endTime))
                            .build()
            )){
                map.put("code", 200);
                map.put("msg", "success");
            }else {
                map.put("code", 500);
                map.put("msg", "error");
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

}


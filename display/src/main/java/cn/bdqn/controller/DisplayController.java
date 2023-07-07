package cn.bdqn.controller;


import cn.bdqn.entity.Display;
import cn.bdqn.service.FileUploadService;
import cn.bdqn.service.IDisplayService;
import cn.bdqn.util.DateTimeUtil;
import cn.bdqn.util.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 显示内容 前端控制器
 * </p>
 *
 * @author dddqmmx
 * @since 2023-06-09
 */
@Controller
@ResponseBody
@CrossOrigin
@RequestMapping("display")
public class DisplayController {

    @Autowired
    private IDisplayService displayService;
    @Autowired
    private FileUploadService fileUploadService;

    @RequestMapping("getPushEveryFuckingDayList")
    public PageInfo<Display> getPushEveryFuckingDayList(
            @RequestParam(required = false,defaultValue = "") String title,
            @RequestParam(required = false,defaultValue = "1") Integer pageNum){
        PageHelper.startPage(pageNum, 2);
        LambdaQueryWrapper<Display> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Display::getDisplayTypeId,2);
        lambdaQueryWrapper.like(Display::getTitle,"%"+title+"%");
        List<Display> displayList = displayService.list(lambdaQueryWrapper);
        return new PageInfo(displayList);
    }

    @RequestMapping("getExternalPerformanceList")
    public PageInfo<Display> getExternalPerformanceList(
            @RequestParam(required = false,defaultValue = "") String title,
            @RequestParam(required = false,defaultValue = "1") Integer pageNum){
        PageHelper.startPage(pageNum, 2);
        LambdaQueryWrapper<Display> lambdaQueryWrapper = new LambdaQueryWrapper<Display>();
        lambdaQueryWrapper.eq(Display::getDisplayTypeId,3);
        lambdaQueryWrapper.like(Display::getTitle,"%"+title+"%");
        List<Display> displayList = displayService.list(lambdaQueryWrapper);
        PageInfo pageInfo = new PageInfo(displayList);
        return pageInfo;
    }

    @RequestMapping("deleteDisplay")

    public Map<String, Object> deleteDisplay(@RequestParam(value = "id") Integer id){
        boolean b = displayService.removeById(id);
        Map<String,Object> map = new HashMap<>();
        if (b){
            map.put("code", 200);
            map.put("msg", "success");
        }else {
            map.put("code", 500);
            map.put("msg", "error");
        }
        return map;
    }

    @RequestMapping("getDisplayById")
    @HystrixCommand(commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")})
    public Display getDisplayById(@RequestParam(value = "id") Integer id){
        return displayService.getById(id);
    }


    @RequestMapping("addDisplay")
    public Map<String, Object> addDisplay(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "displayTypeId") Integer displayTypeId,
            @RequestParam(value = "coverImage", defaultValue = "1", required = false) String coverImage,
            @RequestParam(value = "publishUserId", defaultValue = "1", required = false) Integer publishUserId,
            @RequestParam(value = "createTime", required = false) String createTime)
    {
        Map<String,Object> map = new HashMap<>();
        Date parse = null;
        try {
            parse = DateTimeUtil.sdf.parse(createTime);
            System.out.println(parse);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        if (displayService.save(
                Display.builder()
                        .title(title)
                        .displayTypeId(displayTypeId)
                        .content(content)
                        .coverImage(coverImage)
                        .publishUserId(publishUserId)
                        .createTime(parse)
                        .updateTime(new Date())
                        .build()
        )){
            map.put("code", 200);
            map.put("msg", "success");
        }else {
            map.put("code", 500);
            map.put("msg", "error");
        }
        return map;
    }

    @RequestMapping("getDisplayIdByCreationTimeAndTitle")
    public Integer getCreatedByCreationTimeAndTitle(
            @RequestParam(value = "createTime") String createTime,
            @RequestParam(value = "title")String title
    ){
        LambdaQueryWrapper<Display> lambdaQueryWrapper = new LambdaQueryWrapper<Display>();
        lambdaQueryWrapper.eq(Display::getTitle,title);
        lambdaQueryWrapper.eq(Display::getCreateTime, createTime);
        List<Display> list = displayService.list(lambdaQueryWrapper);
        return list.get(0).getId();
    }

    @RequestMapping("updateDisplay")
    public Map<String, Object> updateDisplay(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "coverImage") String coverImage)
    {
        Map<String,Object> map = new HashMap<>();
        LambdaUpdateWrapper<Display> lambdaUpdateWrapper =  new LambdaUpdateWrapper<Display>();
        lambdaUpdateWrapper.eq(Display::getId, id);
        lambdaUpdateWrapper.set(Display::getTitle, title);
        lambdaUpdateWrapper.set(Display::getContent, content);
        lambdaUpdateWrapper.set(Display::getUpdateTime,new Date());
        lambdaUpdateWrapper.set(Display::getCoverImage,coverImage);

        if(displayService.update(null, lambdaUpdateWrapper)){
            map.put("code", 200);
            map.put("msg", "success");
        }else {
            map.put("code", 500);
            map.put("msg", "error");
        }
        return map;
    }

    @PostMapping("/saveCoverImage")
    public Map<String, Object> saveCoverImage(
            @RequestPart("file") MultipartFile file
    ){
        Map<String,Object> map = new HashMap<>();
        if (!file.isEmpty()){
            Result cover = fileUploadService.fileUpload(file, "cover");
                map.put("code", 200);
                map.put("msg", "success");
                map.put("imgUrl", cover.getData());
        } else {
            map.put("code", 500);
            map.put("msg", "error");
        }
        return map;
    }

    @PostMapping("/upload")
    public String upload(@RequestPart("file") MultipartFile file) {
        return String.valueOf(fileUploadService.fileUpload(file,"display").getData());
    }
}


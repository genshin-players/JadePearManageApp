package cn.bdqn.controller;

import cn.bdqn.client.ActivatesClient;
import cn.bdqn.client.DisplayClient;
import cn.bdqn.client.UserClient;
import cn.bdqn.dto.ActivitiesDTO;
import cn.bdqn.dto.DisplayDTO;
import cn.bdqn.entity.Users;
import cn.bdqn.vo.ResultVO;
import cn.bdqn.vo.displayvo.ActivitiesPageVO;
import cn.bdqn.vo.displayvo.DisplayPageVO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Controller
@RequestMapping("/display")
public class DisplayController {
    @Autowired
    private DisplayClient displayClient;
    @Autowired
    private ActivatesClient activatesClient;
    @Autowired
    private UserClient userClient;

    @RequestMapping("/export_activities_excel")
    public String exportActivitiesExcel(HttpServletResponse response){
        response.setContentType("application/binary;charset=utf-8");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            String fileName = new String(("activities").getBytes(), StandardCharsets.UTF_8);
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
            XSSFWorkbook workbook = new XSSFWorkbook();
            // 2. 创建工作表
            XSSFSheet sheet = workbook.createSheet("WriterDataTest");
            // 3. 模拟待写入数据
            Map<String,Object[]> data = new TreeMap<>();
            data.put("1", new Object[] {"ID","标题","报名人数", "报名开始时间", "报名结束时间","点赞"});
            List<ActivitiesDTO> list = activatesClient.activitiesList("",1).getList();
            int id = 2;
            for (ActivitiesDTO activitiesDTO:list){

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                data.put(String.valueOf(id++),new Object[]{activitiesDTO.getId(),activitiesDTO.getDisplay().getTitle(), activitiesDTO.getSignupNumber(), sdf.format(activitiesDTO.getStartTime()), sdf.format(activitiesDTO.getEndTime()), activitiesDTO.getLikes()});
            }
            //4. 遍历数据写入表中
            Set<String> keySet = data.keySet();
            int rowNum = 0;
            for (String key : keySet){
                Row row = sheet.createRow(rowNum++);
                Object [] objArr = data.get(key);
                int cellNum = 0;
                for (Object obj: objArr){
                    Cell cell  = row.createCell(cellNum++);
                    if (obj instanceof String){
                        cell.setCellValue((String)obj);
                    }else if(obj instanceof Integer){
                        cell.setCellValue((Integer)obj);
                    }
                }
            }
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/export_daily_excel")
    public String exportDailyExcel(HttpServletResponse response){
        response.setContentType("application/binary;charset=utf-8");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            String fileName = new String(("daily").getBytes(), StandardCharsets.UTF_8);
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
            XSSFWorkbook workbook = new XSSFWorkbook();
            // 2. 创建工作表
            XSSFSheet sheet = workbook.createSheet("WriterDataTest");
            // 3. 模拟待写入数据
            Map<String,Object[]> data = new TreeMap<>();
            data.put("1", new Object[] {"ID","标题", "创建时间", "修改时间"});
            DisplayPageVO list = displayClient.getPushEveryFuckingDayList("",1);
            int id = 2;
            for (DisplayDTO displayDTO:list.getList()){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                data.put(String.valueOf(id++),new Object[]{displayDTO.getId(),displayDTO.getTitle(),sdf.format(displayDTO.getCreateTime()), sdf.format(displayDTO.getUpdateTime())});
            }
            //4. 遍历数据写入表中
            Set<String> keySet = data.keySet();
            int rowNum = 0;
            for (String key : keySet){
                Row row = sheet.createRow(rowNum++);
                Object [] objArr = data.get(key);
                int cellNum = 0;
                for (Object obj: objArr){
                    Cell cell  = row.createCell(cellNum++);
                    if (obj instanceof String){
                        cell.setCellValue((String)obj);
                    }else if(obj instanceof Integer){
                        cell.setCellValue((Integer)obj);
                    }
                }
            }
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/export_external_performance_excel")
    public String exportExternalPerformanceExcel(HttpServletResponse response){
        response.setContentType("application/binary;charset=utf-8");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            String fileName = new String(("external_performance").getBytes(), StandardCharsets.UTF_8);
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
            XSSFWorkbook workbook = new XSSFWorkbook();
            // 2. 创建工作表
            XSSFSheet sheet = workbook.createSheet("WriterDataTest");
            // 3. 模拟待写入数据
            Map<String,Object[]> data = new TreeMap<>();
            data.put("1", new Object[] {"ID","标题", "创建时间", "修改时间"});
            DisplayPageVO list = displayClient.getExternalPerformanceList("",1);
            int id = 2;
            for (DisplayDTO displayDTO:list.getList()){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                data.put(String.valueOf(id++),new Object[]{displayDTO.getId(),displayDTO.getTitle(),sdf.format(displayDTO.getCreateTime()), sdf.format(displayDTO.getUpdateTime())});
            }
            //4. 遍历数据写入表中
            Set<String> keySet = data.keySet();
            int rowNum = 0;
            for (String key : keySet){
                Row row = sheet.createRow(rowNum++);
                Object [] objArr = data.get(key);
                int cellNum = 0;
                for (Object obj: objArr){
                    Cell cell  = row.createCell(cellNum++);
                    if (obj instanceof String){
                        cell.setCellValue((String)obj);
                    }else if(obj instanceof Integer){
                        cell.setCellValue((Integer)obj);
                    }
                }
            }
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/daily_info")
    public String toDailyInfo(@RequestParam(required = false,defaultValue = "") String title,
                              @RequestParam(required = false,defaultValue = "1") String pageNum,
                              Model model) {
        DisplayPageVO pushEveryFuckingDayList = displayClient.getPushEveryFuckingDayList(title, Integer.parseInt(pageNum));
        for (DisplayDTO displayDTO : pushEveryFuckingDayList.getList()){
            Users userById = userClient.getUserById(displayDTO.getPublishUserId());
            JSONObject jsonObject = JSON.parseObject(userById.getIdentityInfo());
            String realName = (String) jsonObject.get("realname");
            displayDTO.setPublishUserName(realName);
        }
        model.addAttribute("displayDTOList",pushEveryFuckingDayList.getList());
        model.addAttribute("pages",pushEveryFuckingDayList.getPages());
        model.addAttribute("now",Integer.parseInt(pageNum));
        return "display/daily_info";
    }
    @RequestMapping("/inner_activities")
    public String toInnerActivities(@RequestParam(required = false,defaultValue = "") String title,
                                    @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                    Model model) {
        ActivitiesPageVO activitiesList = activatesClient.activitiesList(title,pageNum);
        for (ActivitiesDTO activitiesDTO : activitiesList.getList()){
            DisplayDTO displayDTO = activitiesDTO.getDisplay();
            Users userById = userClient.getUserById(displayDTO.getPublishUserId());
            JSONObject jsonObject = JSON.parseObject(userById.getIdentityInfo());
            String realName = (String) jsonObject.get("realname");
            displayDTO.setPublishUserName(realName);
            System.out.println(realName);
        }
        model.addAttribute("activitiesList",activitiesList.getList());
        model.addAttribute("pages",activitiesList.getPages());
        model.addAttribute("now",pageNum);
        return "display/inner_activities";
    }

    @RequestMapping("/external_performance")
    public String toExternalPerformance(
            @RequestParam(required = false,defaultValue = "") String title,
            @RequestParam(required = false,defaultValue = "1") Integer pageNum,
            Model model) {
        DisplayPageVO displayDTOList = displayClient.getExternalPerformanceList(title,pageNum);
        for (DisplayDTO displayDTO : displayDTOList.getList()){
            Users userById = userClient.getUserById(displayDTO.getPublishUserId());
            JSONObject jsonObject = JSON.parseObject(userById.getIdentityInfo());
            String realName = (String) jsonObject.get("realname");
            displayDTO.setPublishUserName(realName);
        }
        model.addAttribute("displayDTOList",displayDTOList.getList());
        model.addAttribute("pages",displayDTOList.getPages());
        model.addAttribute("now",pageNum);
        return "display/external_performance";
    }

    /**
     *
     * @param type 内容类型
     * @param signupNum 报名人傻
     * @param startTime 报名开始时间
     * @param endTime 报名结束时间
     * @param model 模型
     * @since 2023/6/13
     * @author ZedFeorius
     * @return editor.ftlh
     */
    @RequestMapping("/editor")
    public String toEditor(
            @RequestParam(required = false) String type,
            @RequestParam(required = false, defaultValue = "-1") String activitiesId,
            @RequestParam(required = false) String signupNum,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime,
            @RequestParam(required = false, defaultValue = "-1") String displayId,
            @RequestParam(required = false, defaultValue = "") String coverImage,
            Model model) {
        model.addAttribute("type", type.trim().equals("") ? "Display" : type);
        model.addAttribute("activitiesId", "undefined".equals(activitiesId.trim()) ? "-1" : activitiesId);
        model.addAttribute("signupNum", signupNum == null ? "" : signupNum);
        model.addAttribute("startTime", startTime == null ? "" : startTime);
        model.addAttribute("endTime", endTime == null ? "" : endTime);
        model.addAttribute("coverImage", coverImage == null ? "-1" : coverImage);
        model.addAttribute("display",("-1".equals(displayId)||"undefined".equals(displayId)) ? DisplayDTO.builder().id(-1).title("标题").content("").build() : displayClient.getDisplayById(Integer.parseInt(displayId))) ;
        return "display/editor";
    }

    @RequestMapping("/edit_activities")
    public String toEditorActivities(@RequestParam(required = false) String id, Model model){
        //System.out.println(id);
        if (id!=null){
            model.addAttribute("activities",activatesClient.getActivitiesById(Integer.parseInt(id)));
        }
        return "display/edit_activities";
    }

    @RequestMapping("deleteDisplayById")
    @ResponseBody
    public Map<String, Object> deleteDisplayById(@RequestParam(value = "id") Integer id){
        return displayClient.deleteDisplay(id);
    }

/*
    @RequestMapping("getDisplayById")
    public DisplayDTO getDisplayById(@RequestParam(value = "id") Integer id){
        ResultVO<DisplayDTO> displayById = displayClient.getDisplayById(id);
        return displayById.getData();
    }
*/


    @RequestMapping("deleteActivitiesById")
    @ResponseBody
    Map<String, Object> deleteDisplay(@RequestParam(value = "activitiesId") Integer activitiesId, @RequestParam(value = "displayId") Integer displayId){
        return activatesClient.deleteActivitiesById(activitiesId, displayId);
    }


    @RequestMapping("insertDisplay")
    @ResponseBody
    public Map<String, Object> addDisplay(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "displayTypeId") Integer displayTypeId,
            @RequestParam(value = "coverImage", defaultValue = "1", required = false) String coverImage,
            @RequestParam(value = "publishUserId", defaultValue = "1", required = false) Integer publishUserId,
            @RequestParam(value = "createTime", required = false) String createTime
    ){
        return displayClient.addDisplay(title, content, displayTypeId, coverImage, publishUserId, createTime);
    }

    @RequestMapping("insertActivities")
    @ResponseBody
    public Map<String, Object> insertActivies(
            @RequestParam(value = "signupNum") Integer signupNumber,
            @RequestParam(value = "startTime") String startTime,
            @RequestParam(value = "endTime") String endTime,
            @RequestParam(value = "createTime") String createTime,
            @RequestParam(value = "title") String title
    ){
        return activatesClient.addActivities(displayClient.getCreatedByCreationTimeAndTitle(createTime, title),signupNumber, startTime, endTime);
    }

    @RequestMapping("updDisplay")
    @ResponseBody
    public Map<String, Object> updDisplay(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "coverImage") String coverImage)
    {
        return displayClient.updateDisplay(id,title, content,coverImage);
    }

    @RequestMapping("updActivities")
    @ResponseBody
    public Map<String, Object> updActivities(
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "signupNumber") Integer signupNumber,
            @RequestParam(value = "startTime") String startTime,
            @RequestParam(value = "endTime") String endTime)
    {
        return activatesClient.updateActivities(id,signupNumber,startTime,endTime);
    }

    @PostMapping("updCoverImage")
    @ResponseBody
    public Map<String, Object> saveCoverImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("id") String id)
    {
        return displayClient.saveCoverImage(file, id);
    }

    @PostMapping("uploadImage")
    @ResponseBody
    public String upload(@RequestPart MultipartFile file){
        return displayClient.upload(file);
    }
}

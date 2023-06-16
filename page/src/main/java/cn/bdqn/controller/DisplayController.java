package cn.bdqn.controller;

import cn.bdqn.client.ActivatesClient;
import cn.bdqn.client.DisplayClient;
import cn.bdqn.client.UserClient;
import cn.bdqn.dto.ActivitiesDTO;
import cn.bdqn.dto.DisplayDTO;
import cn.bdqn.entity.Users;
import cn.bdqn.vo.ResultVO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/display")
public class DisplayController {
    @Autowired
    private DisplayClient displayClient;
    @Autowired
    private ActivatesClient activatesClient;
    @Autowired
    private UserClient userClient;
    @RequestMapping("/daily_info")
    public String toDailyInfo(@RequestParam(required = false,defaultValue = "") String title,Model model) {
        List<DisplayDTO> displayDTOList = displayClient.getPushEveryFuckingDayList(title);
        for (DisplayDTO displayDTO : displayDTOList){
            Users userById = userClient.getUserById(displayDTO.getPublishUserId());
            JSONObject jsonObject = JSON.parseObject(userById.getIdentityInfo());
            String realName = (String) jsonObject.get("realname");
            displayDTO.setPublishUserName(realName);
        }
        model.addAttribute("displayDTOList",displayDTOList);
        return "display/daily_info";
    }
    @RequestMapping("/inner_activities")
    public String toInnerActivities(@RequestParam(required = false,defaultValue = "") String title,Model model) {
        List<ActivitiesDTO> activitiesList = null;
        if (title == null|| "".equals(title)){
            activitiesList = activatesClient.getActivitiesList();
        }else {
            activitiesList = activatesClient.activitiesListByTitle(title);
        }
        for (ActivitiesDTO activitiesDTO : activitiesList){
            DisplayDTO displayDTO = activitiesDTO.getDisplay();
            Users userById = userClient.getUserById(displayDTO.getPublishUserId());
            JSONObject jsonObject = JSON.parseObject(userById.getIdentityInfo());
            String realName = (String) jsonObject.get("realname");
            displayDTO.setPublishUserName(realName);
            System.out.println(realName);
        }
        model.addAttribute("activitiesList",activitiesList);
        return "display/inner_activities";
    }

    @RequestMapping("/external_performance")
    public String toExternalPerformance(@RequestParam(required = false,defaultValue = "") String title,Model model) {
        List<DisplayDTO> displayDTOList = displayClient.getExternalPerformanceList(title);
        for (DisplayDTO displayDTO : displayDTOList){
            Users userById = userClient.getUserById(displayDTO.getPublishUserId());
            JSONObject jsonObject = JSON.parseObject(userById.getIdentityInfo());
            String realName = (String) jsonObject.get("realname");
            displayDTO.setPublishUserName(realName);
        }
        model.addAttribute("displayDTOList",displayDTOList);
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
            Model model) {
        model.addAttribute("type", type.trim().equals("") ? "Display" : type);
        model.addAttribute("activitiesId", "undefined".equals(activitiesId.trim()) ? "-1" : activitiesId);
        model.addAttribute("signupNum", signupNum == null ? "" : signupNum);
        model.addAttribute("startTime", startTime == null ? "" : startTime);
        model.addAttribute("endTime", endTime == null ? "" : endTime);
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
            @RequestParam(value = "content") String content)
    {
        return displayClient.updateDisplay(id,title, content);
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
}

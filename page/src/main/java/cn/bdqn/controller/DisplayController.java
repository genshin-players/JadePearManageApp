package cn.bdqn.controller;

import cn.bdqn.client.ActivatesClient;
import cn.bdqn.client.DisplayClient;
import cn.bdqn.dto.ActivitiesDTO;
import cn.bdqn.dto.DisplayDTO;
import cn.bdqn.vo.ResultVO;
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
    @RequestMapping("/daily_info")
    public String toDailyInfo(@RequestParam(required = false,defaultValue = "") String title,Model model) {
        ResultVO<List<DisplayDTO>> pushEveryFuckingDayList = displayClient.getPushEveryFuckingDayList(title);
        List<DisplayDTO> displayDTOList = pushEveryFuckingDayList.getData();
/*        for (DisplayDTO displayDTO:displayDTOList){
            System.out.println(displayDTO.getTitle());
        }*/
        model.addAttribute("displayDTOList",displayDTOList);
        return "display/daily_info";
    }
    @RequestMapping("/inner_activities")
    public String toInnerActivities(@RequestParam(required = false,defaultValue = "") String title,Model model) {
        List<ActivitiesDTO> activitiesList = null;
        if (title == null|| "".equals(title)){
            activitiesList = activatesClient.getActivitiesList();
        }else {
            activitiesList = activatesClient.activitiesListByTitle(title).getData();
        }
        model.addAttribute("activitiesList",activitiesList);
        return "display/inner_activities";
    }

    @RequestMapping("/external_performance")
    public String toExternalPerformance(@RequestParam(required = false,defaultValue = "") String title,Model model) {
        ResultVO<List<DisplayDTO>> externalPerformanceList = displayClient.getExternalPerformanceList(title);
        List<DisplayDTO> displayDTOList = externalPerformanceList.getData();
/*        for (DisplayDTO displayDTO:displayDTOList){
            System.out.println(displayDTO.getTitle());
        }*/
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
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String signupNum,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime,
            Model model) {
        model.addAttribute("type", type.trim().equals("") ? "Display" : type);
        model.addAttribute("id", id.trim().equals("") ? "-1" : id);
        model.addAttribute("signupNum", signupNum == null ? "" : signupNum);
        model.addAttribute("startTime", startTime == null ? "" : startTime);
        model.addAttribute("endTime", endTime == null ? "" : endTime);
        return "display/editor";
    }

    @RequestMapping("/edit_activities")
    public String toEditorActivities(@RequestParam(required = false) String id, Model model){
        //System.out.println(id);
        if (id!=null){
            ResultVO<ActivitiesDTO> activitiesById = activatesClient.getActivitiesById(Integer.parseInt(id));
            model.addAttribute("activities",activitiesById.getData());
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
}

package cn.bdqn.client;

import cn.bdqn.dto.ActivitiesDTO;
import cn.bdqn.dto.DisplayDTO;
import cn.bdqn.vo.ResultVO;
import cn.bdqn.vo.displayvo.ActivitiesPageVO;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FeignClient(name = "display")
@RequestMapping("/activities")
public interface ActivatesClient {

    @RequestMapping("/activitiesList")
    ActivitiesPageVO activitiesList(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(required = false,defaultValue = "1") Integer pageNum);

    @RequestMapping("deleteActivitiesById")
    Map<String, Object> deleteActivitiesById(@RequestParam(value = "activitiesId") Integer activitiesId, @RequestParam(value = "displayId") Integer displayId);

    @RequestMapping("addActivities")
    Map<String, Object> addActivities(
            @RequestParam(value = "displayId") Integer displayId,
            @RequestParam(value = "signupNum") Integer signupNumber,
            @RequestParam(value = "startTime") String startTime,
            @RequestParam(value = "endTime") String endTime);

    @RequestMapping("/getActivitiesById")
    ActivitiesDTO getActivitiesById(@RequestParam("id") Integer id);
    @RequestMapping("updateActivities")
    Map<String, Object> updateActivities(
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "signupNum") Integer signupNumber,
            @RequestParam(value = "startTime") String startTime,
            @RequestParam(value = "endTime") String endTime);
}

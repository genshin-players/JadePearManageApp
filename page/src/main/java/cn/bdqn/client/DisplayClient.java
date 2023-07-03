package cn.bdqn.client;

import cn.bdqn.dto.DisplayDTO;
import cn.bdqn.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author ZedFeorius
 * @version 1.0.0
 * @date 06 11 2023  17:30:37
 * @packageName cn.bdqn.client
 * @className DisplayClient
 * @describe TODO
 */
@FeignClient(name = "display")
@RequestMapping("display")
public interface DisplayClient {
    @RequestMapping("getPushEveryFuckingDayList")
    List<DisplayDTO> getPushEveryFuckingDayList(@RequestParam(required = false,defaultValue = "") String title);
    @RequestMapping("getExternalPerformanceList")
    List<DisplayDTO> getExternalPerformanceList(@RequestParam(required = false,defaultValue = "") String title);
    @RequestMapping("deleteDisplay")
    Map<String, Object> deleteDisplay(@RequestParam(value = "id") Integer id);
    @RequestMapping("getDisplayById")
    DisplayDTO getDisplayById(@RequestParam(value = "id") Integer id);
    @RequestMapping("addDisplay")
    Map<String, Object> addDisplay(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "displayTypeId") Integer displayTypeId,
            @RequestParam(value = "coverImage", defaultValue = "1", required = false) String coverImage,
            @RequestParam(value = "publishUserId", defaultValue = "1", required = false) Integer publishUserId,
            @RequestParam(value = "createTime", required = false) String createTime);
    @RequestMapping("getDisplayIdByCreationTimeAndTitle")
    Integer getCreatedByCreationTimeAndTitle(
            @RequestParam(value = "createTime") String createTime,
            @RequestParam(value = "title")String title
    );
    @RequestMapping("updateDisplay")
    Map<String, Object> updateDisplay(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "content") String content);
    @RequestMapping("/saveCoverImage")
    Map<String, Object> saveCoverImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("id") String id);
}

package cn.bdqn.client;

import cn.bdqn.dto.ActivitiesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "display")
@RequestMapping("/activities")
public interface ActivatesClient {
    @RequestMapping("/getActivitiesById")
    ActivitiesDTO getActivitiesById(@RequestParam("id") Integer id);
}

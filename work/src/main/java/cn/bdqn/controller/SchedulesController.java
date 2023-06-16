package cn.bdqn.controller;

import cn.bdqn.service.SchedulesService;
import cn.bdqn.vo.MemberWorkCardInfoVO;
import cn.bdqn.vo.MemberWorkDetailInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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


}

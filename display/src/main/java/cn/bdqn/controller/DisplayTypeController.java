package cn.bdqn.controller;


import cn.bdqn.entity.DisplayType;
import cn.bdqn.service.IDisplayTypeService;
import cn.bdqn.service.impl.DisplayTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 显示类型 前端控制器
 * </p>
 *
 * @author dddqmmx
 * @since 2023-06-09
 */


@RestController
@RequestMapping("/displayType")
public class DisplayTypeController {

    @Autowired
    private IDisplayTypeService displayTypeService;

    @RequestMapping("getDisplayTypeList")
    public Map<String, Object> getDisplayTypeList(){
        Map<String,Object> map = new HashMap<>();
        List<DisplayType> list = displayTypeService.list();
        if (list != null){
            map.put("code", 200);
            map.put("msg", "success");
            map.put("data", list);
        }else {
            map.put("code", 500);
            map.put("msg", "error");
        }
        return map;
    }
}


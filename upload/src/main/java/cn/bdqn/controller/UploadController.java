package cn.bdqn.controller;


import cn.bdqn.service.FileUploadService;
import cn.bdqn.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 上传控制器
 * </p>
 *
 * @author ZedFeorius
 * @since 2023-06-09
 */
@RestController
@CrossOrigin
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private FileUploadService fileUploadService;

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


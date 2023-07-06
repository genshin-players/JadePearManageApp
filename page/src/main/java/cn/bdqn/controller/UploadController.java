package cn.bdqn.controller;

import cn.bdqn.client.UploadClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author ZedFeorius
 * @version 1.0.0
 * @date 07-06-2023  09-12-55
 * @packageName cn.bdqn.controller
 * @className UploadController
 * @describe TODO
 */
@RequestMapping("/upload")
@RestController
public class UploadController {
    @Autowired
    private UploadClient uploadClient;
    @PostMapping("updCoverImage")
    @ResponseBody
    public Map<String, Object> saveCoverImage(@RequestParam("file") MultipartFile file)
    {
        return uploadClient.saveCoverImage(file);
    }

    @PostMapping("uploadImage")
    @ResponseBody
    public String upload(@RequestPart MultipartFile file){
        return uploadClient.upload(file);
    }
}

package cn.bdqn.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @author ZedFeorius
 * @version 1.0.0
 * @date 06 11 2023  17:30:37
 * @packageName cn.bdqn.client
 * @className DisplayClient
 * @describe TODO
 */
@FeignClient(name = "upload")
@RequestMapping("upload")
public interface UploadClient {
    @PostMapping(value = "/saveCoverImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Map<String, Object> saveCoverImage(@RequestPart("file") MultipartFile file);
    @PostMapping("/upload")
    String upload(@RequestPart("file") MultipartFile file);
    @PostMapping(value = "/saveAvatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Map<String, Object> saveAvatar(@RequestPart("file") MultipartFile file);
}

package cn.bdqn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller("/file")
public class FileController {

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("fileToUpload") MultipartFile file) throws IOException {
        // 检查上传的文件是否为空
        if (file.isEmpty()) {
            return "请选择要上传的文件";
        }

        // 获取上传文件的原始文件名
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // 创建保存上传文件的路径
        String uploadDir = "D:\\Four_Two_XM\\two\\page\\src\\main\\resources\\static\\image";
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdir();
        }

        // 保存文件到指定路径
        String filePath = uploadDir + fileName;
        file.transferTo(new File(filePath));

        return "文件上传成功！";
    }
}

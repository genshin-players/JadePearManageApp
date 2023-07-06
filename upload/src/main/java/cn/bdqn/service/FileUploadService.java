package cn.bdqn.service;

import cn.bdqn.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ZedFeorius
 * @version 1.0.0
 * @date 07-03-2023  14-19-32
 * @packageName cn.bdqn.service
 * @className FileUploadService
 * @describe TODO
 */
public interface FileUploadService {
    /**
     *
     * @param file 上传文件
     * @param savePath 在服务器中保存的路径，如：coverimage, contente, avator等
     * @return Result，通过.getData即可获取上传路径
     */
    Result fileUpload(MultipartFile file, String savePath);
}

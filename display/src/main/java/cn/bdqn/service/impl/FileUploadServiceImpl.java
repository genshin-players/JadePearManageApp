package cn.bdqn.service.impl;

import cn.bdqn.service.FileUploadService;
import cn.bdqn.util.FtpUtil;
import cn.bdqn.util.IDUtils;
import cn.bdqn.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ZedFeorius
 * @version 1.0.0
 * @date 07-03-2023  14-20-42
 * @packageName cn.bdqn.service.impl
 * @className FileUploadServiceImpl
 * @describe TODO
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Override
    public Result fileUpload(MultipartFile file, String savePath) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd");
            String path = sdf.format(new Date());
            String newFilename = IDUtils.genImageName()+
                    file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            FtpUtil.uploadFile("39.107.229.253",21,"admin","1145141919810","/upload/"+savePath,path,newFilename,file.getInputStream());
            String imageURL = "http://" + "39.107.229.253" + path + newFilename;
            return Result.ok(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.sky.controller.admin;


import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;
    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file){
        log.info("文件上传，文件名：{}",file.getOriginalFilename());
        try {
            //获取上传的文件的字节数组
            byte[] bytes = file.getBytes();
            //获取原始文件名
            String originalFilename = file.getOriginalFilename();
            String substring = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            String objectName = UUID.randomUUID().toString() + substring;
            //上传到OSS
            String upload = aliOssUtil.upload(bytes, objectName);
            return Result.success(upload);
        } catch (Exception e) {
            log.error("文件上传失败：{}",e.getMessage());
            e.printStackTrace();
        }
        return Result.error("文件上传失败");
    }
}

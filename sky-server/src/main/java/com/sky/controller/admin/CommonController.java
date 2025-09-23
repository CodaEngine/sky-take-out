package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * 分类管理
 */
@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {

    @Autowired
    AliOssUtil aliOssUtil;
    @RequestMapping("/upload")
    public Result<String> upload(MultipartFile file){
        log.info("文件上传开始");
        try {
            String originalFileName = file.getOriginalFilename();
            log.info("文件原始名称：{}", originalFileName);
            String extendName = originalFileName.substring(originalFileName.lastIndexOf("."));
            log.info("文件扩展名：{}", extendName);
            String objectName = UUID.randomUUID().toString() + extendName;
            log.info("文件上传路径：{}", objectName);
            String filePath =aliOssUtil.upload(file.getBytes(), objectName);
            log.info("文件上传成功：{}", filePath);
            return Result.success(filePath);
        } catch (Exception e) {
            log.error("文件上传失败：{}", e);
        }

        return null;
    }
}

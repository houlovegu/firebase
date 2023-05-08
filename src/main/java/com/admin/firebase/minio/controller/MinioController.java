package com.admin.firebase.minio.controller;

import cn.hutool.core.lang.Dict;
import com.admin.common.entity.ObjectItem;
import com.admin.common.response.Result;
import com.admin.firebase.minio.service.MinioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "minio文件存储")
@RestController
@RequestMapping("/minio")
public class MinioController {

    @Resource
    MinioService minioService;

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public Result upload(@RequestPart(value = "file") MultipartFile file, @RequestParam(name = "bucketName", required = false) String bucketName) {
        return minioService.upload(file, bucketName);
    }

    @ApiOperation("获取桶中文件列表")
    @PostMapping("/getListByBucket")
    public Result getListByBucket() {
        return minioService.listObjects();
    }

    @ApiOperation("桶是否存在")
    @PostMapping("/existBucket")
    public Result existBucket(@RequestBody String bucketName) {
        return minioService.existBucket(bucketName);
    }

    @ApiOperation("创建桶")
    @PostMapping("/makeBucket")
    public Result makeBucket(@RequestBody String bucketName) {
        return minioService.makeBucket(bucketName);
    }

    @ApiOperation("删除桶")
    @PostMapping("/removeBucket")
    public Result removeBucket(@RequestBody String bucketName) {
        return minioService.removeBucket(bucketName);
    }

    @ApiOperation("获取文件地址")
    @ApiImplicitParam(name = "dict", value = "dict是一个map的增强类，用于封装对象，此接口用于封装bucketName,fileName", type = "body")
    @PostMapping("/getFileUrl")
    public Result getFileUrl(@RequestBody Dict dict) {
        return minioService.getFileUrl(dict);
    }


    @ApiOperation("下载文件")
    @GetMapping("/loadFile")
    public void loadFile(@RequestParam("filePath") String filePath, HttpServletResponse response) {
        minioService.loadFile(filePath, response);
    }

}

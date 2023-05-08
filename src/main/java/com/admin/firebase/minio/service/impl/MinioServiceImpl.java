package com.admin.firebase.minio.service.impl;

import cn.hutool.core.lang.Dict;
import com.admin.common.entity.ObjectItem;
import com.admin.common.response.Result;
import com.admin.common.utils.MinioUtils;
import com.admin.firebase.minio.service.MinioService;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

@Service
public class MinioServiceImpl implements MinioService {

    @Resource
    private MinioUtils minioUtils;

    @Value("${minio.endpoint}")
    private String address;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Override
    public Result upload(MultipartFile file, String bucketName) {
        String upload = minioUtils.upload(file, bucketName);
        String minioFileName = upload.split("/")[1];
        String fileUrl = minioUtils.getFileUrl(null , minioFileName);
        return Result.ok(fileUrl);
    }

    @Override
    public Result listObjects() {
        List<ObjectItem> list = minioUtils.listObjects(bucketName);
        return Result.ok(list);
    }

    @Override
    public Result existBucket(String bucketName) {
        boolean bool = minioUtils.existBucket(bucketName);
        return Result.ok(bool);
    }

    @Override
    public Result makeBucket(String bucketName) {
        Boolean bool = minioUtils.makeBucket(bucketName);
        return Result.ok(bool);
    }

    @Override
    public Result removeBucket(String bucketName) {
        Boolean bool = minioUtils.removeBucket(bucketName);
        return Result.ok(bool);
    }

    @Override
    public Result getFileUrl(Dict dict) {
        String bucketName = dict.getStr("bucketName");
        String fileName = dict.getStr("fileName");
        String fileUrl = minioUtils.getFileUrl(bucketName, fileName);
        return Result.ok(fileUrl);
    }

    @Override
    public void loadFile(String filePath, HttpServletResponse response) {
        try {
            InputStream fileInputStream = minioUtils.getObject(bucketName, filePath);
            response.setHeader("Content-Disposition", "attachment;filename=" + filePath);
            response.setContentType("application/force-download");
            response.setCharacterEncoding("UTF-8");
            IOUtils.copy(fileInputStream, response.getOutputStream());
//            byte[] bytes = minioUtils.download(filePath);
//            ServletOutputStream outputStream = response.getOutputStream();
//            response.setHeader("Content-Disposition", "attachment;filename=" + filePath);
//            response.setHeader("Content-Type", "application/octet-stream");
//            response.setContentType("application/octet-stream; charset=UTF-8");
//            outputStream.write(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

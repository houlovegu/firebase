package com.admin.firebase.minio.service;

import cn.hutool.core.lang.Dict;
import com.admin.common.response.Result;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface MinioService {

    Result upload(MultipartFile file, String bucketName);

    Result listObjects();

    Result existBucket(String bucketName);

    Result makeBucket(String bucketName);

    Result removeBucket(String bucketName);

    Result getFileUrl(Dict dict);

    void loadFile(String filePath, HttpServletResponse response);
}

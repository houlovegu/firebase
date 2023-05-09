package com.admin.firebase.minio.service;

import cn.hutool.core.lang.Dict;
import com.admin.common.response.Result;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface MinioService {

    /**
     * @Author sky
     * @Description 文件上传
     * @Date 2023/5/9 9:00
     * @Param [file, bucketName]
     * @return com.admin.common.response.Result
     **/
    Result upload(MultipartFile file, String bucketName);

    Result listObjects();

    Result existBucket(String bucketName);

    Result makeBucket(String bucketName);

    Result removeBucket(String bucketName);

    /**
     * @Author sky
     * @Description 获取文件url
     * @Date 2023/5/9 9:00
     * @Param [dict]
     * @return com.admin.common.response.Result
     **/
    Result getFileUrl(Dict dict);

    /**
     * @Author sky
     * @Description 文件下载
     * @Date 2023/5/9 9:00
     * @Param [filePath, response]
     * @return void
     **/
    void loadFile(String filePath, HttpServletResponse response);
}

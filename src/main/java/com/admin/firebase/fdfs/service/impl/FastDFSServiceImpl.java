//package com.admin.firebase.fdfs.service.impl;
//
//import com.admin.common.response.Result;
//import com.admin.common.utils.FastDFSClientWrapper;
//import com.admin.firebase.fdfs.service.FastDFSService;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.annotation.Resource;
//import java.io.IOException;
//
//@Service
//public class FastDFSServiceImpl implements FastDFSService {
//
//    @Resource
//    private FastDFSClientWrapper fastDFSClientWrapper;
//
//
//    @Override
//    public Result uploadFile(MultipartFile file) {
//        String filePath = null;
//        try {
//            filePath = fastDFSClientWrapper.uploadFile(file);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return Result.ok(filePath);
//    }
//}

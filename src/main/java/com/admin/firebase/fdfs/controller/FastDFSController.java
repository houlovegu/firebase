//package com.admin.firebase.fdfs.controller;
//
//import com.admin.common.response.Result;
//import com.admin.firebase.fdfs.service.FastDFSService;
//import io.swagger.annotations.Api;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.annotation.Resource;
//
//@RestController
//@Api(tags = "文件上传下载模块")
//@RequestMapping("/fdfs")
//public class FastDFSController {
//
//    @Resource
//    private FastDFSService fastDFSService;
//
//    @PostMapping("/uploadFile")
//    public Result uploadFile(@RequestPart MultipartFile file) {
//        return fastDFSService.uploadFile(file);
//    }
//}

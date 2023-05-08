package com.admin.firebase.fdfs.service;

import com.admin.common.response.Result;
import org.springframework.web.multipart.MultipartFile;

public interface FastDFSService {

    Result uploadFile(MultipartFile file);
}

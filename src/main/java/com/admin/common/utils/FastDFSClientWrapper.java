//package com.admin.common.utils;
//
//import cn.hutool.core.util.StrUtil;
//import com.github.tobato.fastdfs.domain.fdfs.MetaData;
//import com.github.tobato.fastdfs.domain.fdfs.StorePath;
//import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
//import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
//import com.github.tobato.fastdfs.service.FastFileStorageClient;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.io.Charsets;
//import org.apache.commons.io.FilenameUtils;
//import org.apache.commons.io.IOUtils;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletResponse;
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.net.URLEncoder;
//import java.nio.charset.Charset;
//import java.util.Set;
//
//@Slf4j
//@Component
//public class FastDFSClientWrapper {
//    @Resource
//    private FastFileStorageClient storageClient;
//
//
//    /**
//     * 上传文件
//     *
//     * @param file 文件对象
//     * @return 文件访问地址
//     * @throws IOException
//     */
//    public String uploadFile(MultipartFile file) throws IOException {
//        StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
//        return getAccessUrl(storePath);
//    }
//
//    /**
//     * 将一段字符串生成一个文件上传
//     *
//     * @param content       文件内容
//     * @param fileExtension
//     * @return
//     */
//    public String uploadFile(String content, String fileExtension) {
//        byte[] buff = content.getBytes(Charset.forName("UTF-8"));
//        ByteArrayInputStream stream = new ByteArrayInputStream(buff);
//        StorePath storePath = storageClient.uploadFile(stream, buff.length, fileExtension, null);
//        return getAccessUrl(storePath);
//    }
//    private String getAccessUrl(StorePath storePath) {
//        String fileUrl = storePath.getFullPath();
//        return fileUrl;
//    }
//    /**
//     * 删除文件
//     *
//     * @param fileUrl 文件访问地址
//     * @return
//     */
//    public void deleteFile(String fileUrl) {
//        if (StrUtil.isEmpty(fileUrl)) {
//            return;
//        }
//        try {
//            StorePath storePath = StorePath.parseFromUrl(fileUrl);
//            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
//        } catch (FdfsUnsupportStorePathException e) {
//            log.warn(e.getMessage());
//        }
//    }
//
//    /**
//     * 下载文件
//     *
//     * @param filePath       文件路径
//     * @return
//     */
//    public void downloadFile(String filePath, HttpServletResponse response) throws IOException {
//        String group = filePath.substring(0, filePath.indexOf("/"));
//        String path = filePath.substring(filePath.indexOf("/") + 1);
//        Set<MetaData> metadata = storageClient.getMetadata(group, path);
//        String fileName = metadata.iterator().next().getValue();
//        byte[] bytes = storageClient.downloadFile(group, path, new DownloadByteArray());
//        response.setContentType("application/octet-stream");
//        response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, Charsets.UTF_8.displayName()));
//        IOUtils.write(bytes,response.getOutputStream());
//    }
//}

package com.cq.kt.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Tool:IntelliJ IDEA
 * Date:2024-06-25-10:12
 * Description:
 *
 * @ Author:两袖青蛇
 */
public interface FileService {
    //文件上传
    String upload(MultipartFile file);
}

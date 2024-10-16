package com.cq.kt.vod.controller;

import com.cq.kt.result.Result;
import com.cq.kt.vod.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Tool:IntelliJ IDEA
 * Date:2024-06-25-10:07
 * Description:
 *
 * @ Author:两袖青蛇
 */
@ResponseBody
@Api("上传跟新头像")
@Controller
//@CrossOrigin
@RequestMapping("/vod/file")
public class FileUploadController {
    @Autowired
    private FileService fileService;
    /**
     * 文件上传
     */
    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public Result upload(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) {
        String uploadUrl = fileService.upload(file);
        return Result.ok(uploadUrl).message("文件上传成功");
    }

}

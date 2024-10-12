package com.cq.kt.vod.api;

import com.cq.kt.result.Result;
import com.cq.kt.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Tool:IntelliJ IDEA
 * Date:2024-07-07-18:42
 * Description:
 *
 * @ Author:两袖青蛇
 */
@Api(tags = "腾讯视频点播")
@RestController
@RequestMapping("/api/vod")
public class VodApiController {

    @Autowired
    private VodService vodService;

    @GetMapping("getPlayAuth/{courseId}/{videoId}")
    public Result getPlayAuth(
            @ApiParam(value = "课程id", required = true)
            @PathVariable Long courseId,
            @ApiParam(value = "视频id", required = true)
            @PathVariable Long videoId) {
        return  Result.ok(vodService.getPlayAuth(courseId, videoId));
    }
}

package com.cq.kt.vod.service;


import com.cq.kt.model.vod.VideoVisitor;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 视频来访者记录表 服务类
 * </p>
 *
 * @author 六千里
 * @since 2024-06-30
 */
public interface VideoVisitorService extends IService<VideoVisitor> {

    Map<String, Object> findCount(Long courseId, String startDate, String endDate);
}

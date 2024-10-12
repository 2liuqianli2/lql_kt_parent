package com.cq.kt.vod.service;


import com.cq.kt.model.vod.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author 六千里
 * @since 2024-06-27
 */
public interface VideoService extends IService<Video> {

    //根据课程id删除小节
    void removeVideoByCourseId(Long id);

    void removeVideoById(Long id);
}

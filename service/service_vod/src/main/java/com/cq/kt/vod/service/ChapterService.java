package com.cq.kt.vod.service;


import com.cq.kt.model.vod.Chapter;
import com.cq.kt.vo.vod.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 六千里
 * @since 2024-06-27
 */
public interface ChapterService extends IService<Chapter> {
    //章节小结列表
    List<ChapterVo> getNestedTreeList(Long courseId);

    //根据课程id删除章节
    void removeChapterByCourseId(Long id);
}

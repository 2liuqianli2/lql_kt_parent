package com.cq.kt.vod.mapper;


import com.cq.kt.model.vod.Course;
import com.cq.kt.vo.vod.CoursePublishVo;
import com.cq.kt.vo.vod.CourseVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author 六千里
 * @since 2024-06-27
 */
public interface CourseMapper extends BaseMapper<Course> {
    public CoursePublishVo selectCoursePublishVoById(Long id);

    CourseVo selectCourseVoById(Long id);
}

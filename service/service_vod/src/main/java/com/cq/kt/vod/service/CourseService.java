package com.cq.kt.vod.service;


import com.cq.kt.model.vod.Course;
import com.cq.kt.vo.vod.CourseFormVo;
import com.cq.kt.vo.vod.CoursePublishVo;
import com.cq.kt.vo.vod.CourseQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 六千里
 * @since 2024-06-27
 */
public interface CourseService extends IService<Course> {

    //课程列表
    Map<String,Object> findPage(Page<Course> pageParam, CourseQueryVo courseQueryVo);

    Long saveCourseInfo(CourseFormVo courseFormVo);

    //根据id获取课程信息
    CourseFormVo getCourseFormVoById(Long id);

    //根据id修改课程信息
    void updateCourseById(CourseFormVo courseFormVo);

    //根据id获取课程发布信息
    CoursePublishVo getCoursePublishVo(Long id);

    //根据id发布课程
    boolean publishCourseById(Long id);

    //根据id删除课程
    void removeCourseById(Long id);

    //根据课程分类查询课程列表（分页）公众号接口
    Map<String, Object> apiFindPage(Page<Course> pageParam, CourseQueryVo courseQueryVo);

    //根据id查询课程 公众号接口
    Map<String, Object> getInfoById(Long courseId);
}

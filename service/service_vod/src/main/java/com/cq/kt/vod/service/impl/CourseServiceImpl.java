package com.cq.kt.vod.service.impl;


import com.cq.kt.model.vod.Course;
import com.cq.kt.model.vod.CourseDescription;
import com.cq.kt.model.vod.Subject;
import com.cq.kt.model.vod.Teacher;

import com.cq.kt.vo.vod.*;
import com.cq.kt.vod.mapper.CourseMapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cq.kt.vod.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 六千里
 * @since 2024-06-27
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {


        @Autowired
        private TeacherService teacherService;

        @Autowired
        private SubjectService subjectService;

        @Autowired
        private CourseDescriptionServiceImpl descriptionService;

        @Resource
        private CourseMapper courseMapper;

        @Autowired
        private VideoService videoService;

        @Autowired
        private ChapterService chapterService;

        //课程列表
        @Override
        public Map<String,Object> findPage(Page<Course> pageParam, CourseQueryVo courseQueryVo) {
            //获取条件值
            String title = courseQueryVo.getTitle();//名称
            Long subjectId = courseQueryVo.getSubjectId();//二级分类
            Long subjectParentId = courseQueryVo.getSubjectParentId();//一级分类
            Long teacherId = courseQueryVo.getTeacherId();//讲师
            //封装条件
            QueryWrapper<Course> wrapper = new QueryWrapper<>();
            if (!StringUtils.isEmpty(title)) {
                wrapper.like("title", title);
            }
            if (!StringUtils.isEmpty(subjectId)) {
                wrapper.eq("subject_id", subjectId);
            }
            if (!StringUtils.isEmpty(subjectParentId)) {
                wrapper.eq("subject_parent_id", subjectParentId);
            }
            if (!StringUtils.isEmpty(teacherId)) {
                wrapper.eq("teacher_id", teacherId);
            }
            //调用方法查询
            Page<Course> pages = baseMapper.selectPage(pageParam, wrapper);
            long totalCount = pages.getTotal();//总记录数
            long totalPage = pages.getPages();//总页数
            long currentPage = pages.getCurrent();//当前页
            long size = pages.getSize();//每页记录数
            //每页数据集合
            List<Course> records = pages.getRecords();
            //遍历封装讲师和分类名称
            records.stream().forEach(item -> {
                this.getTeacherOrSubjectName(item);
            });
            //封装返回数据
            Map<String, Object> map = new HashMap<>();
            map.put("totalCount", totalCount);
            map.put("totalPage", totalPage);
            map.put("records", records);
            return map;
        }

    //获取讲师和分类名称
    private Course getTeacherOrSubjectName(Course course) {
        //查询讲师名称
        Teacher teacher = teacherService.getById(course.getTeacherId());
        if(teacher != null) {
            course.getParam().put("teacherName",teacher.getName());
        }
        //查询分类名称
        Subject subjectOne = subjectService.getById(course.getSubjectParentId());
        if(subjectOne != null) {
            course.getParam().put("subjectParentTitle",subjectOne.getTitle());
        }
        Subject subjectTwo = subjectService.getById(course.getSubjectId());
        if(subjectTwo != null) {
            course.getParam().put("subjectTitle",subjectTwo.getTitle());
        }
        return course;
    }

    //根据id获取课程信息
    @Override
    public CourseFormVo getCourseFormVoById(Long id) {
        //从course表中取数据
        Course course = baseMapper.selectById(id);
        if(course == null){
            return null;
        }
        //从course_description表中取数据
        CourseDescription courseDescription = descriptionService.getById(id);
        //创建courseInfoForm对象
        CourseFormVo courseFormVo = new CourseFormVo();
        BeanUtils.copyProperties(course, courseFormVo);
        if(courseDescription != null){
            courseFormVo.setDescription(courseDescription.getDescription());
        }
        return courseFormVo;
    }
    //实现方法
//添加课程基本信息
    @Override
    public Long saveCourseInfo(CourseFormVo courseFormVo) {
        //保存课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo, course);
        baseMapper.insert(course);

        //保存课程详情信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseFormVo.getDescription());
        courseDescription.setId(course.getId());
        descriptionService.save(courseDescription);

        //返回课程id
        return course.getId();
    }

    //根据id修改课程信息
    @Override
    public void updateCourseById(CourseFormVo courseFormVo) {
        //修改课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo, course);
        baseMapper.updateById(course);
        //修改课程详情信息
        CourseDescription courseDescription = descriptionService.getById(course.getId());
        courseDescription.setDescription(courseFormVo.getDescription());
        courseDescription.setId(course.getId());
        descriptionService.updateById(courseDescription);

    }

    //根据id获取课程发布信息
    @Override
    public CoursePublishVo getCoursePublishVo(Long id) {
        return courseMapper.selectCoursePublishVoById(id);
    }

    //根据id发布课程
    @Override
    public boolean publishCourseById(Long id) {
        Course course = new Course();
        course.setId(id);
        course.setPublishTime(new Date());
        course.setStatus(1);
        return this.updateById(course);
    }

    //删除课程
    @Override
    public void removeCourseById(Long id) {
        //根据课程id删除小节
        videoService.removeVideoByCourseId(id);
        //根据课程id删除章节
        chapterService.removeChapterByCourseId(id);
        //根据课程id删除描述
        descriptionService.removeById(id);
        //根据课程id删除课程
        baseMapper.deleteById(id);
    }
    //课程列表 公众号接口
    @Override
    public Map<String, Object> apiFindPage(Page<Course> pageParam, CourseQueryVo courseQueryVo) {
        //获取条件值
        String title = courseQueryVo.getTitle();//名称
        Long subjectId = courseQueryVo.getSubjectId();//二级分类
        Long subjectParentId = courseQueryVo.getSubjectParentId();//一级分类
        Long teacherId = courseQueryVo.getTeacherId();//讲师
        //封装条件
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(title)) {
            wrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(subjectId)) {
            wrapper.eq("subject_id",subjectId);
        }
        if(!StringUtils.isEmpty(subjectParentId)) {
            wrapper.eq("subject_parent_id",subjectParentId);
        }
        if(!StringUtils.isEmpty(teacherId)) {
            wrapper.eq("teacher_id",teacherId);
        }
        //调用方法查询
        Page<Course> pages = baseMapper.selectPage(pageParam, wrapper);

        long totalCount = pages.getTotal();//总记录数
        long totalPage = pages.getPages();//总页数
        long currentPage = pages.getCurrent();//当前页
        long size = pages.getSize();//每页记录数
        //每页数据集合
        List<Course> records = pages.getRecords();
        records.stream().forEach(item -> {
            this.getApiTeacherOrSubjectName(item);
        });

        Map<String,Object> map = new HashMap<>();
        map.put("totalCount",totalCount);
        map.put("totalPage",totalPage);
        map.put("records",records);

        return map;
    }


    //获取讲师和分类名称 公众号接口
    private Course getApiTeacherOrSubjectName(Course course) {
        Teacher teacher = teacherService.getById(course.getTeacherId());
        if(teacher != null) {
            course.getParam().put("teacherName",teacher.getName());
        }

        Subject subjectOne = subjectService.getById(course.getSubjectParentId());
        if(subjectOne != null) {
            course.getParam().put("subjectParentTitle",subjectOne.getTitle());
        }
        Subject subjectTwo = subjectService.getById(course.getSubjectId());
        if(subjectTwo != null) {
            course.getParam().put("subjectTitle",subjectTwo.getTitle());
        }
        return course;
    }

    //根据id查询课程 公众号接口
    //根据id查询课程
    @Override
    public Map<String, Object> getInfoById(Long id) {
        //更新流量量
        Course course = baseMapper.selectById(id);
        course.setViewCount(course.getViewCount() + 1);
        baseMapper.updateById(course);

        Map<String, Object> map = new HashMap<>();
        CourseVo courseVo = baseMapper.selectCourseVoById(id);
        List<ChapterVo> chapterVoList = chapterService.getNestedTreeList(id);
        CourseDescription courseDescription = descriptionService.getById(id);
        Teacher teacher = teacherService.getById(course.getTeacherId());

        //TODO后续完善
        Boolean isBuy = false;

        map.put("courseVo", courseVo);
        map.put("chapterVoList", chapterVoList);
        map.put("description", null != courseDescription ?
                courseDescription.getDescription() : "");
        map.put("teacher", teacher);
        map.put("isBuy", isBuy);//是否购买
        return map;
    }

}

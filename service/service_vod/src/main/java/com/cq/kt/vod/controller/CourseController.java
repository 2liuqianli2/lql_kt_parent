package com.cq.kt.vod.controller;


import com.cq.kt.model.vod.Course;
import com.cq.kt.result.Result;
import com.cq.kt.vo.vod.CourseFormVo;
import com.cq.kt.vo.vod.CoursePublishVo;
import com.cq.kt.vo.vod.CourseQueryVo;
import com.cq.kt.vod.service.ChapterService;
import com.cq.kt.vod.service.CourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 六千里
 * @since 2024-06-27
 */
@Api(tags = "课程管理接口")
@RestController
@RequestMapping("/vod/course")
//@CrossOrigin
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private ChapterService chapterService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseVo", value = "查询对象", required = false)
            CourseQueryVo courseQueryVo) {
        Page<Course> pageParam = new Page<>(page, limit);
        Map<String,Object> map = courseService.findPage(pageParam, courseQueryVo);
        return Result.ok(map);
    }
    //添加课程基本信息
    @ApiOperation(value = "新增")
    @PostMapping("save")
    public Result save(@RequestBody CourseFormVo courseFormVo) {
        Long courseId = courseService.saveCourseInfo(courseFormVo);
        return Result.ok(courseId);
    }

    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        CourseFormVo course = courseService.getCourseFormVoById(id);
        return Result.ok(course);
    }

    @ApiOperation(value = "修改")
    @PutMapping("update")
    public Result updateById(@RequestBody CourseFormVo courseFormVo) {
        courseService.updateCourseById(courseFormVo);
        return Result.ok(courseFormVo.getId());
    }

    @ApiOperation("根据id获取课程发布信息")
    @GetMapping("getCoursePublishVo/{id}")
    public Result getCoursePublishVoById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable Long id){

        CoursePublishVo coursePublishVo = courseService.getCoursePublishVo(id);
        return Result.ok(coursePublishVo);
    }

    @ApiOperation("根据id发布课程")
    @PutMapping("publishCourseById/{id}")
    public Result publishCourseById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable Long id){

        boolean result = courseService.publishCourseById(id);
        return Result.ok();
    }

    @ApiOperation(value = "删除课程")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        courseService.removeCourseById(id);
        chapterService.removeChapterByCourseId(id);
        return Result.ok();
    }
}


package com.cq.kt.vod.api;

import com.cq.kt.model.vod.Course;
import com.cq.kt.result.Result;
import com.cq.kt.vo.vod.CourseQueryVo;
import com.cq.kt.vod.service.ChapterService;
import com.cq.kt.vod.service.CourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Tool:IntelliJ IDEA
 * Date:2024-07-03-20:44
 * Description:
 *
 * @ Author:两袖青蛇
 */
@RestController()
public class CourseApiController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ChapterService chapterService;

    @ApiOperation("根据关键字查询课程")

    @GetMapping("/api/findByKeyword/{keyword}")
    public List<Course> findByKeyword(
            @ApiParam(value = "关键字", required = true)
            @PathVariable("keyword") String keyword){


        QueryWrapper<Course> queryWrapper = new QueryWrapper();
        queryWrapper.like("title", keyword);
        List<Course> list = courseService.list(queryWrapper);
        return list;
    }


    //根据课程分类查询课程列表（分页）
    @ApiOperation("根据课程分类查询课程列表")
    @GetMapping("/api/vod/course/{subjectParentId}/{page}/{limit}")
    public Result findPageCourse(@ApiParam(value = "课程一级分类ID", required = true) @PathVariable Long subjectParentId,
                                 @ApiParam(name = "page", value = "当前页码", required = true) @PathVariable Long page,
                                 @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable Long limit) {
        //封装条件
        CourseQueryVo courseQueryVo = new CourseQueryVo();
        courseQueryVo.setSubjectParentId(subjectParentId);
        //创建page对象
        Page<Course> pageParam = new Page<>(page,limit);
        Map<String,Object> map = courseService.apiFindPage(pageParam,courseQueryVo);
        return Result.ok(map);
    }

    //根据ID查询课程
    @ApiOperation("根据ID查询课程")
    @GetMapping("/api/vod/course/getInfo/{courseId}")
    public Result getInfo(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable Long courseId){
        Map<String, Object> map = courseService.getInfoById(courseId);
        return Result.ok(map);
    }
}

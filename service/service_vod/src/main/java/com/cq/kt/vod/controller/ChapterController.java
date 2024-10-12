package com.cq.kt.vod.controller;


import com.cq.kt.model.vod.Chapter;
import com.cq.kt.result.Result;
import com.cq.kt.vo.vod.ChapterVo;
import com.cq.kt.vod.service.ChapterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 六千里
 * @since 2024-06-27
 */
@RestController
@RequestMapping("/vod/chapter")
//@CrossOrigin
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    //获取章节小结列表
    @ApiOperation("嵌套章节数据列表")
    @GetMapping("getNestedTreeList/{courseId}")
    public Result getNestedTreeList(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable Long courseId){

        List<ChapterVo> chapterVoList = chapterService.getNestedTreeList(courseId);
        return Result.ok(chapterVoList);
    }

    //2 添加章节
    @PostMapping("save")
    public Result save(@RequestBody Chapter chapter) {
        chapterService.save(chapter);
        return Result.ok(null);
    }

    //3 修改-根据id查询
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Chapter chapter = chapterService.getById(id);
        return Result.ok(chapter);
    }

    //4 修改-最终实现
    @PostMapping("update")
    public Result update(@RequestBody Chapter chapter) {
        chapterService.updateById(chapter);
        return Result.ok(null);
    }

    //5 删除章节
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        chapterService.removeById(id);
        return Result.ok(null);
    }
}


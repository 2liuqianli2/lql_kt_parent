package com.cq.kt.vod.controller;


import com.cq.kt.model.vod.Subject;
import com.cq.kt.result.Result;
import com.cq.kt.vod.service.SubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author 六千里
 * @since 2024-06-25
 */
@RestController
@RequestMapping("/vod/subject")
//@CrossOrigin
public class SubjectController {
    @Autowired
    private SubjectService subjectService;
    //查询下一层课程分类
    //根据parent_id
    @ApiOperation("查询下一层的课程分类")
    @GetMapping("getChildSubject/{id}")
    public Result getChildSubject(@PathVariable Long id) {
        List<Subject> list = subjectService.selectList(id);
        return Result.ok(list);
    }
    @ApiOperation(value="导出")
    @GetMapping(value = "/exportData")
    public void exportData(HttpServletResponse response) {
        subjectService.exportData(response);
    }

    @ApiOperation(value = "导入")
    @PostMapping("importData")
    public Result importData(MultipartFile file) throws Exception {
        subjectService.importDictData(file);
        return Result.ok();
    }
}


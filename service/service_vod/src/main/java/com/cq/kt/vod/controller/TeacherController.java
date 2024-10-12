package com.cq.kt.vod.controller;



import com.cq.kt.model.vod.Teacher;
import com.cq.kt.result.Result;
import com.cq.kt.vo.vod.TeacherQueryVo;
import com.cq.kt.vod.service.impl.TeacherServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author 六千里
 * @since 2024-06-20
 */

@RestController
@RequestMapping("/vod/teacher")
//@CrossOrigin
public class TeacherController {
    @Resource
    private TeacherServiceImpl teacherService;
    @ApiOperation(value = "查询所教师")
    @GetMapping("findall")
    public Result findAll(){

        List<Teacher> list = teacherService.list();
        return Result.ok(list);
    }
    @ApiOperation(value = "通过id 删除")
    @DeleteMapping("delete/{id}")
    public Result deleteId(@PathVariable Long id){
        boolean b = teacherService.removeById(id);
        if(b){
            return Result.ok(b);
        }
        return Result.fail();
    }

    //分页查询
    @ApiOperation(value = "分页查询")
    @PostMapping("findQueryPage/{current}/{limit}")
    public Result findPage(@PathVariable Long current , @PathVariable Long limit,
                           @RequestBody TeacherQueryVo teacher){


        Page<Teacher> page = new Page(current,limit);
        if(teacher==null){
            Page<Teacher> page1 = teacherService.page(page, null);
            return Result.ok(page1);
        }
        LambdaQueryWrapper<Teacher> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(!StringUtils.isEmpty(teacher.getName()),Teacher::getName,teacher.getName())
                .ge(!StringUtils.isEmpty(teacher.getJoinDateBegin()),Teacher::getJoinDate,teacher.getJoinDateBegin())
                .le(!StringUtils.isEmpty(teacher.getJoinDateEnd()),Teacher::getJoinDate,teacher.getJoinDateEnd())
                .eq(!StringUtils.isEmpty(teacher.getLevel()),Teacher::getLevel,teacher.getLevel());

        teacherService.page(page,lambdaQueryWrapper);
        return Result.ok(page);
    }

    // 新增教师
    @ApiOperation(value = "新增")
    @PostMapping("save")
    public Result save(@RequestBody Teacher teacher) {
        teacherService.save(teacher);
        return Result.ok(null);
    }

    //获取教师
    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Teacher teacher = teacherService.getById(id);
        return Result.ok(teacher);
    }
    //修改教师
    @ApiOperation(value = "修改")
    @PutMapping("update")
    public Result updateById(@RequestBody Teacher teacher) {
        teacherService.updateById(teacher);
        return Result.ok(null);
    }
    //根据id批量删除
    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        teacherService.removeByIds(idList);
        return Result.ok(null);
    }
}


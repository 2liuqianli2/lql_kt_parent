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
import java.util.HashMap;
import java.util.Map;

/**
 * Tool:IntelliJ IDEA
 * Date:2024-06-24-8:41
 * Description:
 *
 * @ Author:两袖青蛇
 */
@RestController
@RequestMapping("/admin/vod/user")
//@CrossOrigin
public class LoginController {
    /**
     * 登录
     * @return
     */

    @Resource
    private TeacherServiceImpl teacherService;
    @PostMapping("login")
    public Result login() {
        Map<String, Object> map = new HashMap<>();
        map.put("token","admin");
        return Result.ok(map);
    }
    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("info")
    public Result info() {
        // "lint": "eslint --ext .js,.vue src",
        Map<String, Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name","admin");
        map.put("avatar","https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        return Result.ok(map);
    }
    /**
     * 退出
     * @return
     */
    @PostMapping("logout")
    public Result logout(){
        return Result.ok();
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("findQueryPage/{current}/{limit}")
    public Result findPage(@PathVariable Long current , @PathVariable Long limit,
                           TeacherQueryVo teacher){


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
}

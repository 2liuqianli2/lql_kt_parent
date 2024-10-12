package com.cq.kt.client.course;

import com.cq.kt.model.vod.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Tool:IntelliJ IDEA
 * Date:2024-07-03-20:51
 * Description:
 *
 * @ Author:两袖青蛇
 */
@FeignClient("service-vod")
public interface CourseFeignClient {


    @GetMapping("/api/findByKeyword/{keyword}")
    List<Course> findByKeyword(@PathVariable("keyword") String keyword);
}

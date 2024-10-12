package com.cq.kt.vod.service;

import com.cq.kt.model.vod.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author 六千里
 * @since 2024-06-25
 */
public interface SubjectService extends IService<Subject> {

    //查询下一层课程分类
    List<Subject> selectList(Long id);
    public void exportData(HttpServletResponse response);

    public void importDictData(MultipartFile file) throws IOException;
}

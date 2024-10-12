package com.cq.kt.vod.service.impl;


import com.alibaba.excel.EasyExcel;
import com.cq.kt.model.vod.Subject;
import com.cq.kt.vo.vod.SubjectEeVo;
import com.cq.kt.vod.listener.SubjectListener;
import com.cq.kt.vod.mapper.SubjectMapper;
import com.cq.kt.vod.service.SubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.deploy.net.URLEncoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author 六千里
 * @since 2024-06-25
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {
    @Autowired
    private SubjectListener subjectListener;
    //查询下一层课程分类
    @Override
    public List<Subject> selectList(Long id) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);

        List<Subject> subjectList = baseMapper.selectList(wrapper);
        //向list集合每个Subject对象中设置hasChildren
        for (Subject subject:subjectList) {
            Long subjectId = subject.getId();
            boolean isChild = this.isChildren(subjectId);
            subject.setHasChildren(isChild);
        }
        return subjectList;
    }
    //判断id下面是否有子节点
    private boolean isChildren(Long id) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(wrapper);
        return count>0;
    }

    //课程分类导出
    @Override
    public void exportData(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("课程分类", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");
            List<Subject> dictList = baseMapper.selectList(null);
            List<SubjectEeVo> dictVoList = new ArrayList<>(dictList.size());
            for(Subject dict : dictList) {
                SubjectEeVo dictVo = new SubjectEeVo();
                BeanUtils.copyProperties(dict,dictVo);
                dictVoList.add(dictVo);
            }

            EasyExcel.write(response.getOutputStream(), SubjectEeVo.class).sheet("课程分类").doWrite(dictVoList);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void importDictData(MultipartFile file)  {
        try {
            EasyExcel.read(file.getInputStream(), SubjectEeVo.class,subjectListener).sheet().doRead();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}

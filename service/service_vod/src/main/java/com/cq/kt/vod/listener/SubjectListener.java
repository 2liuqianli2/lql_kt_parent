package com.cq.kt.vod.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.cq.kt.model.vod.Subject;
import com.cq.kt.vo.vod.SubjectEeVo;
import com.cq.kt.vod.mapper.SubjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Tool:IntelliJ IDEA
 * Date:2024-06-27-9:32
 * Description:
 *
 * @ Author:两袖青蛇
 */
@Component
public class SubjectListener extends AnalysisEventListener<SubjectEeVo> {
    @Resource
    private SubjectMapper subjectMapper;

    @Override
    public void invoke(SubjectEeVo subjectEeVo, AnalysisContext analysisContext) {
        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectEeVo,subject);
        subjectMapper.insert(subject);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

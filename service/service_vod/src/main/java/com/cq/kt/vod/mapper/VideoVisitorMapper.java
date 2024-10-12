package com.cq.kt.vod.mapper;


import com.cq.kt.model.vod.VideoVisitor;
import com.cq.kt.vo.vod.VideoVisitorCountVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 视频来访者记录表 Mapper 接口
 * </p>
 *
 * @author 六千里
 * @since 2024-06-30
 */
public interface VideoVisitorMapper extends BaseMapper<VideoVisitor> {

    List<VideoVisitorCountVo> findCount(@Param("courseId") Long courseId,
                                        @Param("startDate")String startDate, @Param("endDate")String endDate);
}

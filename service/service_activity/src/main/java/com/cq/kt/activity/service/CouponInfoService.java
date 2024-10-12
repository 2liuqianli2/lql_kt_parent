package com.cq.kt.activity.service;


import com.cq.kt.model.activity.CouponInfo;
import com.cq.kt.model.activity.CouponUse;
import com.cq.kt.vo.activity.CouponUseQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 优惠券信息 服务类
 * </p>
 *
 * @author 六千里
 * @since 2024-07-02
 */
public interface CouponInfoService extends IService<CouponInfo> {

    //获取已使用优惠券列表
    IPage<CouponUse> selectCouponUsePage(Page<CouponUse> pageParam, CouponUseQueryVo couponUseQueryVo);
}

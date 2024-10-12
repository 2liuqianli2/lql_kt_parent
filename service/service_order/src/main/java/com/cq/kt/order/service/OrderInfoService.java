package com.cq.kt.order.service;



import com.cq.kt.model.order.OrderInfo;
import com.cq.kt.vo.order.OrderInfoQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 订单表 订单表 服务类
 * </p>
 *
 * @author 六千里
 * @since 2024-07-02
 */
public interface OrderInfoService extends IService<OrderInfo> {
    //订单列表
    Map<String, Object> findPageOrderInfo(Page<OrderInfo> pageParam, OrderInfoQueryVo orderInfoQueryVo);
}

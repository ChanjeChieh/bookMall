package com.qianfeng.service;

import com.qianfeng.dao.BookMapper;
import com.qianfeng.dao.OrderDetailMapper;
import com.qianfeng.dao.OrdersMapper;
import com.qianfeng.dao.ShoppingMapper;
import com.qianfeng.pojo.*;
import com.qianfeng.util.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private ShoppingMapper shoppingMapper;
    @Autowired
    private BookMapper bookMapper;

    @Override
    public ResultVo addOrder(Integer[] ids, Double totalMoney, HttpSession session) {
        Users loginUser = (Users) session.getAttribute("loginUser");
        if(loginUser==null){
            return ResultVo.error("未登录");
        }else{
            // 1. 订单主表添加数据(时间、用户id  价格)
            Orders orders = new Orders();
            orders.setCreatetime(new Date());
            orders.setOrderPrice(totalMoney);
            orders.setUid(loginUser.getUid());
            //orders对象执行此添加之前oid没有值，在插入数据之后就有值了
            ordersMapper.addOrder(orders);
            // 2. 订单详情表添加数据(订单主表id、商品id、商品数量)
            for(Integer id:ids){
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOid(orders.getOid());
                //根据购物车的id查询购物车详情，获取商品id和购买的商品数量
                Shopping shopping = shoppingMapper.selectByPrimaryKey(id);
                orderDetail.setNum(shopping.getNum());
                orderDetail.setBid(shopping.getBid());
                orderDetailMapper.addOrderDetail(orderDetail);
            }
            //返回订单主表的oid(便于前端传参查询)
            return ResultVo.success("订单生成",orders.getOid());
        }
    }

    @Override
    public ResultVo deleteOrderById(Integer oid) {
        Integer res = ordersMapper.deleteByPrimaryKey(oid);
        if(res == 1){
            return ResultVo.success("删除成功");
        }else{
            return ResultVo.error("删除失败");
        }
    }

    @Override
    public ResultVo getOrderList(Integer oid) {
        List<OrderDetail> orderDetailList = orderDetailMapper.getOrderDetailListByoid(oid);
        for (OrderDetail orderDetail:orderDetailList){
            Book book = bookMapper.selectByPrimaryKey(orderDetail.getBid());
            orderDetail.setBook(book);
        }
        return ResultVo.success("success",orderDetailList);
    }

    @Override
    public ResultVo updateOrderById(Orders order) {
        int res = ordersMapper.updateByPrimaryKey(order);
        if(res == 1){
            return ResultVo.success("修改成功");
        }else{
            return ResultVo.success("修改失败");
        }
    }

    @Override
    public ResultVo selectAll() {
        List<Orders> orderses = ordersMapper.selectAll();
        return ResultVo.success("查询成功", orderses);
    }

    @Override
    public ResultVo selectOrderById(Integer oid) {
        Orders orders = ordersMapper.selectByPrimaryKey(oid);
        return ResultVo.success("查询成功", orders);
    }
}

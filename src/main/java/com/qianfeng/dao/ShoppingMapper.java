package com.qianfeng.dao;

import com.qianfeng.pojo.Shopping;
import java.util.List;

public interface ShoppingMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(Shopping record);

    Shopping selectByPrimaryKey(Integer sid);

    List<Shopping> selectAll();

    int updateByPrimaryKey(Shopping record);

    /**
     * 添加到购物车
     * @param shopping
     */
    void addShopping(Shopping shopping);

    /**
     * 根据用户id和商品id查询购物车对象
     * @param shopping
     * @return
     */
    Shopping getShoppingByuidAndbid(Shopping shopping);

    /**
     * 根据用户id和商品id更新购物车商品购买数量
     * @param shopping
     */
    void updateCountByuidAndbid(Shopping shopping);

    /**
     * 根据用户id查询购物车
     * @param uid
     * @return
     */
    List<Shopping> getShoppingListByuid(Integer uid);
}
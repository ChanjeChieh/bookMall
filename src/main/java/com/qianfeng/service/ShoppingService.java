package com.qianfeng.service;

import com.qianfeng.pojo.Shopping;
import com.qianfeng.util.ResultVo;

import javax.servlet.http.HttpSession;

public interface ShoppingService {
    ResultVo addShopping(Shopping shopping, HttpSession session);

    ResultVo getShoppingList(HttpSession session);
}

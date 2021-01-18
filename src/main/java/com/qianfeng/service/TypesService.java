package com.qianfeng.service;

import com.qianfeng.pojo.Types;

import java.util.List;

public interface TypesService {

    /**
     * 查询所有菜单，返回树结构菜单
     */
    List<Types> getTypesList();
}

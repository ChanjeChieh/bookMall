package com.qianfeng.service;

import com.qianfeng.pojo.Users;
import com.qianfeng.util.ResultVo;

import javax.servlet.http.HttpSession;

public interface UserService {

    /**
     * 注册
     * @param users
     * @return
     */
    ResultVo regist(Users users);

    ResultVo selectUserById(Integer uid);
    /**
     * 登录
     * @param users
     * @param session
     * @return
     */
    ResultVo login(Users users, HttpSession session);

    ResultVo selectAll(Integer uid);

    ResultVo deleteByPrimaryKey(Integer uid);

    ResultVo updateByPrimaryKey(Users users);
}

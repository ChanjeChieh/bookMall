package com.qianfeng.dao;

import com.qianfeng.pojo.Users;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(Users record);

    Users selectByPrimaryKey(Integer uid);

    List<Users> selectAll();

    int updateByPrimaryKey(Users record);

    Users getUserByUsername(Users users);

    int regist(Users users);

}
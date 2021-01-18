package com.qianfeng.controller;

import com.qianfeng.pojo.Users;
import com.qianfeng.service.UserService;
import com.qianfeng.util.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/regist")
    // axios的post请求要用@RequestBody加实体类接收
    public ResultVo regist(@RequestBody Users users){
        ResultVo resultVo = userService.regist(users);
        return resultVo;
    }
    @RequestMapping("/login")
    public ResultVo login(@RequestBody Users users, HttpSession session){
        ResultVo resultVo = userService.login(users,session);
        return resultVo;
    }
    @RequestMapping("/exit")
    public ResultVo exit(HttpSession session){
        session.removeAttribute("loginUser");
        return ResultVo.success("退出成功");
    }

    @RequestMapping("/check")
    public ResultVo check(HttpSession session){
        Users loginUser = (Users)session.getAttribute("loginUser");
        if(loginUser==null){
            return ResultVo.error("用户未登录，请登录");
        }else{
            return ResultVo.success("success");
        }
    }

    @RequestMapping("/selectAll")
    public ResultVo selectAll(Integer uid){
        ResultVo resultVo = userService.selectAll(uid);
        return resultVo;
    }

    @RequestMapping("/deleteUserById")
    public ResultVo deleteByPrimaryKey(Integer uid){
        ResultVo resultVo = userService.deleteByPrimaryKey(uid);
        return resultVo;
    }

    @RequestMapping("/updateByPrimaryKey")
    public ResultVo updateByPrimaryKey(@RequestBody Users user){
        ResultVo resultVo = userService.updateByPrimaryKey(user);
        return resultVo;
    }

    @RequestMapping("/selectUserById")
    public ResultVo selectUserById(Integer uid){
        ResultVo resultVo = userService.selectUserById(uid);
        return resultVo;
    }


}

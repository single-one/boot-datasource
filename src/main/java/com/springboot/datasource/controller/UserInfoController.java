package com.springboot.datasource.controller;

import com.springboot.datasource.service.UserInfoService;
import com.springboot.datasource.util.PagehelperUtils;
import com.springboot.datasource.util.Result;
import com.springboot.datasource.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/userlist")
    public Result<Object> sayException() throws Exception{
        PagehelperUtils userList = userInfoService.listUser();
        return ResultUtil.success(userList);
    }

    @RequestMapping("/insert")
    public Result<Object> insert() throws Exception {
        userInfoService.insert();
        return ResultUtil.success();
    }
}

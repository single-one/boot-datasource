package com.springboot.datasource.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.datasource.annotation.DynamicDb;
import com.springboot.datasource.annotation.MasterDb;
import com.springboot.datasource.annotation.SlaveDb;
import com.springboot.datasource.dao.UserInfoMapper;
import com.springboot.datasource.entity.UserInfo;
import com.springboot.datasource.entity.UserInfoExample;
import com.springboot.datasource.enums.DbEnum;
import com.springboot.datasource.service.UserInfoService;
import com.springboot.datasource.util.PagehelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    @DynamicDb(value = DbEnum.SLAVE)
    public PagehelperUtils listUser() throws Exception{
        PageHelper.startPage(1,2,true);
        UserInfoExample userInfoExample = new UserInfoExample();
        List<UserInfo> userInfoList = this.userInfoMapper.selectByExample(userInfoExample);
        PageInfo pageInfo = new PageInfo(userInfoList);
        return new PagehelperUtils(pageInfo);
    }

    @Override
    @DynamicDb(value = DbEnum.MASTER)
    public void insert() throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(UUID.randomUUID().toString().replace("-",""));
        userInfo.setGovCode("111222");
        userInfo.setUserName("boot");
        userInfo.setUserCode("000111");
        userInfo.setUserPwd("boot");
        userInfo.setCreateTime(new Date());
        userInfo.setUpdateTime(new Date());
        int index = this.userInfoMapper.insert(userInfo);

    }
}

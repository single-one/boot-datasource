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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private TestB testB;

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

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    @DynamicDb(value = DbEnum.MASTER)
    public void insertA() throws Exception {
        try {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(UUID.randomUUID().toString().replace("-",""));
//        userInfo.setId("aff6dee17483442c82c292ce5219d845");
            userInfo.setGovCode("A");
            userInfo.setUserName("A");
            userInfo.setUserCode("A");
            userInfo.setUserPwd("A");
            userInfo.setCreateTime(new Date());
            userInfo.setUpdateTime(new Date());
            int index = this.userInfoMapper.insert(userInfo);
        } catch (Exception e) {
            throw new Exception("ABC");
        }

    }

    @Override
    @Transactional
    public void insertC() throws Exception {
        try {
            insertA();
            testB.insertB();
        } catch (Exception e){
            System.out.println("Exception");
            throw e;
        }

    }

















    @Override
    @DynamicDb(value = DbEnum.MASTER)
    @Async
    public void insertB() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(UUID.randomUUID().toString().replace("-",""));
        userInfo.setGovCode("B");
        userInfo.setUserName("B");
        userInfo.setUserCode("B");
        userInfo.setUserPwd("B");
        userInfo.setCreateTime(new Date());
        userInfo.setUpdateTime(new Date());
        int index = this.userInfoMapper.insert(userInfo);
        int a = 1 / 0;
    }
}

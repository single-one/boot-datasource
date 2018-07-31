package com.springboot.datasource.service;

import com.springboot.datasource.util.PagehelperUtils;

public interface UserInfoService {
    public PagehelperUtils listUser() throws Exception;

    public void insert() throws Exception;
}

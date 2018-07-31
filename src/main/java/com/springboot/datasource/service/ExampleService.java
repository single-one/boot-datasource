package com.springboot.datasource.service;

import com.springboot.datasource.enums.ResultEnum;
import com.springboot.datasource.exception.ItemException;
import com.springboot.datasource.util.Result;
import com.springboot.datasource.util.ResultUtil;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    public Result<Object> sayExceptionPost(int age){
        if (age < 10){
            throw new ItemException(ResultEnum.PRIMARY_SCHOO);
        }
        if (age > 10 && age < 15){
            throw new ItemException(ResultEnum.JUNIOR_SCHOO);
        }
        return ResultUtil.success();
    }

}

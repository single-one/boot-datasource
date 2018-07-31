package com.springboot.datasource.util;

import com.springboot.datasource.enums.ResultEnum;

public class ResultUtil {
    public static Result success(){
        Result result = new Result();
        result.setRetCode(ResultEnum.SUCCESS.getCode());
        result.setRetMsg(ResultEnum.SUCCESS.getMsg());
        return result;
    }

    public static <T> Result<T> success(T obj){
        Result result = new Result();
        result.setRetCode(ResultEnum.SUCCESS.getCode());
        result.setRetMsg(ResultEnum.SUCCESS.getMsg());
        result.setResult(obj);
        return result;
    }

    public static Result error(String code, String msg){
        Result result = new Result();
        result.setRetCode(code);
        result.setRetMsg(msg);
        return result;
    }

    public static Result error(ResultEnum resultEnum){
        Result result = new Result();
        result.setRetCode(resultEnum.getCode());
        result.setRetMsg(resultEnum.getMsg());
        return result;
    }

}

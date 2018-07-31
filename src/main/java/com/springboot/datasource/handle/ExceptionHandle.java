package com.springboot.datasource.handle;

import com.springboot.datasource.enums.ResultEnum;
import com.springboot.datasource.exception.ItemException;
import com.springboot.datasource.util.Result;
import com.springboot.datasource.util.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        return new ResultUtil().error(ResultEnum.UNKNOWN_ERROR);
    }

    @ExceptionHandler(value = ItemException.class)
    @ResponseBody
    public Result handle(ItemException e){
        return new ResultUtil().error(e.getErrorCode(), e.getErrorMsg());
    }
}

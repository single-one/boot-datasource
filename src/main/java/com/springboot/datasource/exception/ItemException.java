package com.springboot.datasource.exception;

import com.springboot.datasource.enums.ResultEnum;

/**
 * 自定义异常类
 * 注意: 这里 extends RuntimeException 因为 spring 在事务回滚时默认时遇到Runtionexception
 */
public class ItemException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String errorCode;
    private String errorMsg;

    public ItemException(){
        super();
    }
    public ItemException(Throwable ex){
        super(ex);
    }

    public ItemException(String errorCode, String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ItemException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.errorCode = resultEnum.getCode();
        this.errorMsg = resultEnum.getMsg();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

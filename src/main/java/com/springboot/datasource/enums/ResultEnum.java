package com.springboot.datasource.enums;

public enum ResultEnum {
    UNKNOWN_ERROR("110000","未知错误"),
    SUCCESS("000000","成功"),
    PRIMARY_SCHOO("100100","三四年级的小学生"),
    JUNIOR_SCHOO("100200","五六年级的小学生");
    ResultEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

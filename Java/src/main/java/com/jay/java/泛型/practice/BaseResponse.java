package com.jay.java.泛型.practice;

/**
 * Author：Jay On 2019/5/11 20:48
 * <p>
 * Description: 接口数据接收基类
 */
public class BaseResponse {

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

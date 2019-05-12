package com.jay.java.泛型.practice;

/**
 * Author：Jay On 2019/5/11 20:49
 * <p>
 * Description: 用户信息接口实体类
 */
public class UserResponse<T> extends BaseResponse {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

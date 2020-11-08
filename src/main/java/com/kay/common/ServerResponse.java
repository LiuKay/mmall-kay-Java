package com.kay.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * Created by kay on 2018/3/19.
 * 服务端响应对象
 */
//只返回不为空的字段,即value为null,也不返回key
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ServerResponse<T> {

    private int status;
    private String msg;
    private T data;

    private ServerResponse(int status){
        this.status = status;
    }

    private ServerResponse(int status,String message) {
        this.status = status;
        this.msg = message;
    }

    private ServerResponse(int status,T data){
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status, String message, T data) {
        this.status = status;
        this.msg = message;
        this.data = data;
    }

    @JsonIgnore
    //不序列化，留给调用者判断是否成功
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus(){
        return this.status;
    }

    public T getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }


    //只返回成功状态码
    public static <T> ServerResponse<T> createBySuccess() {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    //返回成功状态和数据
    public static <T> ServerResponse<T> createBySuccess(T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data);
    }

    //返回成功状态和提示消息
    public static <T> ServerResponse<T> createBySuccessMessage(String message) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),message);
    }

    //返回成功状态、消息和数据
    public static <T> ServerResponse<T> createBySuccessMessage(String message,T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),message,data);
    }

    //返回失败码
    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDescription());
    }

    //返回失败码和自定义提示消息
    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage) {
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(), errorMessage);
    }

    //返回其他自定义失败码和提示消息
    public static <T> ServerResponse<T> createByErrorCodeMessage(int status, String errorMessage) {
        return new ServerResponse<T>(status, errorMessage);
    }

    public static <T> ServerResponse<T> createByError(Throwable throwable) {
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(), ExceptionUtils.getStackTrace(throwable));
    }
}

package com.zyl.http;

public class R {

    private String message;
    private int code;
    private Object data;
    public R(){}
    public R(String message, int code) {
        this.message = message;
        this.code = code;
    }
    public R(Object data) {
        this.data = data;
    }
    public R(String message) {
        this.message = message;
    }
    public R(String message, int code, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static com.zyl.http.R ok(int code, String message, Object data){
        return new com.zyl.http.R(message,code,data);
    }
    public static com.zyl.http.R ok(String message, Object data){
        return new com.zyl.http.R(message,200,data);
    }
    public static com.zyl.http.R ok(Object data){
        return new com.zyl.http.R("ok",200,data);
    }

    public static com.zyl.http.R ok(String message){
        return new com.zyl.http.R(message);
    }
    public static com.zyl.http.R error(String message){
        return new com.zyl.http.R(message,500,null);
    }

    public static com.zyl.http.R error(int code, String message){
        return new com.zyl.http.R(message,500,null);
    }

}

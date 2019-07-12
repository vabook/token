package top.vabook.token.entity;

import top.vabook.token.enums.HttpStatusEnum;

import java.io.Serializable;

/**
 * @Author: vabook
 * @Date: 2019/7/11 15:45
 * 用作包含返回的对象,由状态码,数据,描述信息组成
 */
public class JsonData implements Serializable {

    private Integer code; // 状态码 对应httpstatus中的状态码
    private Object data; // 数据
    private String msg;// 描述

    public JsonData(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    //return 成功
    public static JsonData buildSuccess(){
        return new JsonData(HttpStatusEnum.SUCCESS.getCode(),null,HttpStatusEnum.SUCCESS.getInfo());
    }

    //return成功,有数据
    public static JsonData buildSuccess(Object data) {
        return new JsonData(HttpStatusEnum.SUCCESS.getCode(), data, HttpStatusEnum.SUCCESS.getInfo());
    }

    //有msg
    public static JsonData buildSuccess(Object data, String msg) {
        return new JsonData(HttpStatusEnum.SUCCESS.getCode(), data, msg);
    }
    //
    public static JsonData buildSuccess(Object data, int code) {
        return new JsonData(code, data, null);
    }

    //返回错误,没有数据
    public static JsonData buildError(Integer code, String msg) {
        return new JsonData(code, null, msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "JsonData{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}

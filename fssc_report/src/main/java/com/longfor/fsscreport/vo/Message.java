package com.longfor.fsscreport.vo;

import java.io.Serializable;



/**
 * 公共返回封装类
 * @author chenziyao
 *
 * @param <T>
 */
public class Message<T> implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;

    private String msg;

    private T data;

    public Message() {
    }

    public Message(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Message(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

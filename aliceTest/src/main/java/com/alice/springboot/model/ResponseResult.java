package com.alice.springboot.model;

import com.alice.springboot.constants.ResponseStatusEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * restful风格响应数据
 */
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = -987626522961999054L;

    private ResponseStatusEnum status;

    private String other;

    private String message;

    private String code;

    private List<String> params = new ArrayList<>();

    private T data;

    public ResponseStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ResponseStatusEnum status) {
        this.status = status;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseResult()
    {
        this.status = ResponseStatusEnum.SUCCESS;
        this.code = "200";
    }

    public ResponseResult(T data)
    {
        this.code = "200";
        this.data = data;
        this.status = ResponseStatusEnum.SUCCESS;
    }
}

package com.webster.response;

import lombok.Data;

@Data
public class BaseResponse<T> {
    private int code;
    private String description;
    private T data;
}

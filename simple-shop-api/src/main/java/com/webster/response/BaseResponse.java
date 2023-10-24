package com.webster.response;

import com.webster.enums.Responses;
import lombok.Data;

import java.util.Map;

@Data
public class BaseResponse<T> {
    private int code;
    private String description;
    private Map<String, String> errors;
    private T data;

    public void setResponse(Responses response) {
        this.code = response.getCode();
        this.description = response.getDescription();
    }
}

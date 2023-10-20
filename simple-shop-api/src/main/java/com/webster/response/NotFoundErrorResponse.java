package com.webster.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter
public class NotFoundErrorResponse {
    private Instant timestamp;
    private String message;
    private String uri;
}

package com.webster.enums;

import lombok.Getter;

@Getter
public enum Responses {

    SUCCESS(0, "Process end with success"),
    NOT_FOUND(404, "Resource not found"),
    GURU_BUSY(999, "We're really sorry! sometimes things go wrong, " +
            "it seems that one of our systems has failed and the guru is at mediation. " +
            "Please, try again in few minutes, maybe we already restore service");

    private final int code;
    private final String description;

    Responses(int code, String description) {
        this.code = code;
        this.description = description;
    }
}

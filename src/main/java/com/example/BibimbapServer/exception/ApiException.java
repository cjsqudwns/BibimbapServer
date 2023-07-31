package com.example.BibimbapServer.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiException {

    //exception으로 client에 어떤 값을 보여줄지 정한다.
    private final String message;
    private final String code;
    private final int httpStatus;
    private String detail;
    private final LocalDateTime timestamp;

    @Builder
    public ApiException(ExceptionCode code, LocalDateTime localDateTime) {
        this.message = code.getMessage();
        this.code = code.getCode();
        this.httpStatus = code.getStatus();
        this.timestamp = localDateTime;
    }

    public void setDetail(String detailMessage) {
        this.detail = detailMessage;
    }
}

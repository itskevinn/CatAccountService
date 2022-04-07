package com.kevcode.cataccountservice.application.shared;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;


public class Response<T> extends ResponseEntity<T> {

    private String message;
    private String exceptionMessage;


    public Response(HttpStatus status) {
        super(status);
    }

    public Response(T body, HttpStatus status) {
        super(body, status);
    }

    public Response(T body, HttpStatus status, String message) {
        super(body, status);
        this.message = message;
    }

    public Response(T body, HttpStatus status, String message, String exceptionMessage) {
        super(body, status);
        this.message = message;
        this.exceptionMessage = exceptionMessage;
    }

    public Response(MultiValueMap<String, String> headers, HttpStatus status) {
        super(headers, status);
    }

    public Response(T body, MultiValueMap<String, String> headers, HttpStatus status) {
        super(body, headers, status);
    }

    public Response(T body, MultiValueMap<String, String> headers, int rawStatus) {
        super(body, headers, rawStatus);
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

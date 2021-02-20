package com.yhy.blog.handler;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoResourceFoundException extends RuntimeException {
    public NoResourceFoundException() {
    }

    public NoResourceFoundException(String message) {
        super(message);
    }

    public NoResourceFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

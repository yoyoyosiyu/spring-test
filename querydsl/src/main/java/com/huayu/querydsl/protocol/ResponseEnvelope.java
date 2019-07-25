package com.huayu.querydsl.protocol;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

public class ResponseEnvelope<T> {

    private Date timestamp;
    private String  status;
    private String  path;
    private T       data;

    public ResponseEnvelope(HttpStatus status, T data) {

        this.status = "" + status.value();
        this.data = data;

        ServletRequestAttributes requestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        this.path = requestAttributes == null ? "" : requestAttributes.getRequest().getRequestURI();

        this.timestamp = new Date();
    }

    public static <T> ResponseEntity build(T data, HttpStatus status) {

        return new ResponseEntity<>(new ResponseEnvelope<>(status, data), status);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

package ru.kata.spring.boot_security.demo.util;

public class UserErrorResponse {
    private String info;

    private long timestamp;
    public UserErrorResponse() {
    }

    public UserErrorResponse(String info, long timestamp) {
        this.info = info;
        this.timestamp = timestamp;
    }



    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

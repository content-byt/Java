package com.example;

public class LogEntry {
    private String timestamp;
    private String requestType;
    private String resource;
    private int statusCode;
    private long responseTime;

    public LogEntry(String timestamp, String requestType, String resource, int statusCode, long responseTime) {
        this.timestamp = timestamp;
        this.requestType = requestType;
        this.resource = resource;
        this.statusCode = statusCode;
        this.responseTime = responseTime;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getRequestType() {
        return requestType;
    }

    public String getResource() {
        return resource;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public long getResponseTime() {
        return responseTime;
    }
}

package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogAnalyzer {
    private List<LogEntry> logEntries;

    public LogAnalyzer() {
        logEntries = new ArrayList<>();
    }

    public void readLogFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                // 去除首尾空格
                line = line.trim();
                System.out.println("读取第 " + lineNumber + " 行: " + line);
                String[] parts = line.split(" ");
                if (parts.length == 5) {
                    String timestamp = parts[0];
                    String requestType = parts[1];
                    String resource = parts[2];
                    int statusCode = Integer.parseInt(parts[3]);
                    long responseTime = Long.parseLong(parts[4]);
                    LogEntry entry = new LogEntry(timestamp, requestType, resource, statusCode, responseTime);
                    logEntries.add(entry);
                    System.out.println("解析成功第 " + lineNumber + " 行");
                } else {
                    System.out.println("跳过第 " + lineNumber + " 行（格式错误，应为5列，实际" + parts.length + "列）");
                }
            }
        } catch (IOException e) {
            System.err.println("读取日志文件时出错: " + e.getMessage());
        }
    }

    public Map<String, Integer> countRequestTypes() {
        Map<String, Integer> requestTypeCount = new HashMap<>();
        for (LogEntry entry : logEntries) {
            String requestType = entry.getRequestType();
            requestTypeCount.put(requestType, requestTypeCount.getOrDefault(requestType, 0) + 1);
        }
        return requestTypeCount;
    }

    public LogEntry findSlowestRequest() {
        if (logEntries.isEmpty()) {
            return null;
        }
        LogEntry slowestEntry = logEntries.get(0);
        for (LogEntry entry : logEntries) {
            if (entry.getResponseTime() > slowestEntry.getResponseTime()) {
                slowestEntry = entry;
            }
        }
        return slowestEntry;
    }

    public Map<Integer, Integer> countStatusCodes() {
        Map<Integer, Integer> statusCodeCount = new HashMap<>();
        for (LogEntry entry : logEntries) {
            int statusCode = entry.getStatusCode();
            statusCodeCount.put(statusCode, statusCodeCount.getOrDefault(statusCode, 0) + 1);
        }
        return statusCodeCount;
    }
}
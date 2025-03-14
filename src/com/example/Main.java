package com.example;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        LogAnalyzer analyzer = new LogAnalyzer();
        try {
            analyzer.readLogFile("E:/RiZhiJianKong/log-analysis-tool/example.log");
        } catch (Exception e) {
            System.err.println("读取日志文件时发生异常: " + e.getMessage());
            e.printStackTrace();
        }

        Map<String, Integer> requestTypeCount = analyzer.countRequestTypes();
        System.out.println("不同请求类型的数量:");
        for (Map.Entry<String, Integer> entry : requestTypeCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        LogEntry slowestRequest = analyzer.findSlowestRequest();
        if (slowestRequest != null) {
            System.out.println("\n响应时间最长的请求:");
            System.out.println("时间戳: " + slowestRequest.getTimestamp());
            System.out.println("请求类型: " + slowestRequest.getRequestType());
            System.out.println("请求资源: " + slowestRequest.getResource());
            System.out.println("响应时间: " + slowestRequest.getResponseTime() + " 毫秒");
        }

        Map<Integer, Integer> statusCodeCount = analyzer.countStatusCodes();
        System.out.println("\n不同状态码的数量:");
        for (Map.Entry<Integer, Integer> entry : statusCodeCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
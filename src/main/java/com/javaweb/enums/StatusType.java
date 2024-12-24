package com.javaweb.enums;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public enum StatusType {
    DXL("Đang xử lý"),
    XL("Đã xử lý"),
    CXL("Chưa xử lý");

    private final String name;

    StatusType(String name) {
        this.name = name;
    }

    public String getStatusTypeName() {
        return name;
    }

    public static Map<String, String> type() {
        Map<String, String> statusTypes = new LinkedHashMap<>();
        for (StatusType type : StatusType.values()) {
            statusTypes.put(type.toString(), type.name);
        }

        return statusTypes;
    }
}

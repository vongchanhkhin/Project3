package com.javaweb.enums;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public enum TransactionType {
    CSKH("Chăm Sóc Khách Hàng"),
    DDX("Dẫn Đi Xem");

    private final String name;

    TransactionType(String name) {
        this.name = name;
    }

    public String getTransactionTypeName() {
        return name;
    }

    public static Map<String, String> type() {
        Map<String, String> typeCodes = new LinkedHashMap<>();
        for (TransactionType type : TransactionType.values()) {
            typeCodes.put(type.toString(), type.name);
        }

        return typeCodes;
    }
}

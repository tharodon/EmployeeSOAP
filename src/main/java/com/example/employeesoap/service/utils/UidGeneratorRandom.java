package com.example.employeesoap.service.utils;
//todo пакет во множественном числе. просто util
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

public class UidGeneratorRandom {

    public String generateUID() {
        byte[] bytes = String.valueOf((Math.random() * Long.MAX_VALUE)).getBytes(StandardCharsets.UTF_8);
        return DigestUtils.md5DigestAsHex(bytes);
    }
}

package com.example.employeesoap.service;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

//todo ммм, сути тут интерфейс не нужен. Удали интерфейс
// done
public class UidGeneratorRandom {

    public String generateUID() {
        byte[] bytes = String.valueOf((Math.random() * Long.MAX_VALUE)).getBytes(StandardCharsets.UTF_8);
        return DigestUtils.md5DigestAsHex(bytes);
    }
}

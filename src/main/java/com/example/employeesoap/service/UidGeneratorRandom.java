package com.example.employeesoap.service;

import com.example.employeesoap.api.UidGenerator;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

public class UidGeneratorRandom implements UidGenerator { //todo ммм, сути тут интерфейс не нужен. Удали интерфейс

    @Override
    public String generateUID() {
        byte[] bytes = String.valueOf((Math.random() * Long.MAX_VALUE)).getBytes(StandardCharsets.UTF_8);
        return DigestUtils.md5DigestAsHex(bytes);
    }
}

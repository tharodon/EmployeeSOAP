package com.example.employeesoap.service;

import com.example.employeesoap.api.UidGenerator;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

public class UIDGeneratorRandom implements UidGenerator { //todo название см туду в интерфейсе
    @Override
    public String generateUID() {
        return DigestUtils.md5DigestAsHex(
                String.valueOf((Math.random() * Long.MAX_VALUE)).getBytes(StandardCharsets.UTF_8)); //todo вынести в скобках переменную
    }
}

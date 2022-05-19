package com.example.employeesoap.security.api;
//todo перенеси в другой пакет api
import com.example.employeesoap.security.entity.User;

public interface UserDao {
    void save(User user);
}

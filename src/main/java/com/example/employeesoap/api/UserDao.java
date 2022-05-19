package com.example.employeesoap.api;
//todo перенеси в другой пакет api
// done

import com.example.employeesoap.entity.User;

public interface UserDao {
    void save(User user);
}

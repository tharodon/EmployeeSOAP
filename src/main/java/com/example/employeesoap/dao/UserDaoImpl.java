package com.example.employeesoap.dao;

import com.example.employeesoap.api.UserDao;
import com.example.employeesoap.entity.User;
import com.example.employeesoap.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}

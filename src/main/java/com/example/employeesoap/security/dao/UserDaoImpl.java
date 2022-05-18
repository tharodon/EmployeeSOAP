package com.example.employeesoap.security.dao;

import com.example.employeesoap.security.api.UserDao;
import com.example.employeesoap.security.entity.User;
import com.example.employeesoap.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

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

package com.example.employeesoap.dao;
//todo не понимаю зачем еще один пакет dao
// done

import com.example.employeesoap.api.UserDao;
import com.example.employeesoap.entity.User;
import com.example.employeesoap.repository.UserRepository;
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

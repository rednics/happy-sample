package com.happyhouse.server.api.service;

import com.happyhouse.server.api.domain.User;
import com.happyhouse.server.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer id) {

        return  userRepository.findById(id);
    }

    public User findByEmail(String email) {

        Map<String, Object> sqlParams = new HashMap<>();
        sqlParams.put("email", email);

        return userRepository.findByEmail(sqlParams);
    }

    public void create(User user) {

        userRepository.create(user);
    }

    public void update(User user) {

        userRepository.update(user);
    }

    public void delete(Integer id) {

        userRepository.delete(id);
    }
}

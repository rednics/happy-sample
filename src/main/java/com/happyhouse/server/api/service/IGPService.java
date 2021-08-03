package com.happyhouse.server.api.service;

import com.happyhouse.server.api.adaptor.client.IGPClient;
import com.happyhouse.server.api.controller.dto.UserDTO;
import com.happyhouse.server.api.controller.mapper.UserMapper;
import com.happyhouse.server.api.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class IGPService {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final IGPClient igpClient;

    public UserDTO findByEmail(String email) {

        User user = igpClient.findUserByEmail("header-value", email);

        return userMapper.toDTO(user);
    }

}

package com.happyhouse.server.api.controller;

import com.happyhouse.server.api.controller.dto.UserDTO;
import com.happyhouse.server.api.controller.mapper.UserMapper;
import com.happyhouse.server.api.domain.User;
import com.happyhouse.server.api.service.IGPService;
import com.happyhouse.server.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Locale;

/*
    http(s)://project.example.com/api/v1/common/management/codes/{codeId}
                                          Part   Module   Resource
    [method]
    query : Collection(GET)
    get   : Object(GET)
    create : (POST)
    update : (PUT)
    modify : (PATCH)
    delete : (DELETE)

*/

@Api("사용자")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/happy/user", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class UserResource {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final UserService userService;
    private final IGPService igpService;
    private final MessageSource messageSource;

    @ApiOperation("등록")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDTO> create(@RequestBody @ApiParam("사용자") User user) {
        userService.create(user);
        URI uri = WebMvcLinkBuilder.linkTo(UserResource.class).slash(user.getId()).toUri();
        UserDTO userDTO = userMapper.toDTO(user);

        /*
            modelMapper.typeMap(Order.class, OrderDTO.class).addMappings(mapper -> {
                mapper.map(src -> src.getBillingAddress().getStreet(),
                        Destination::setBillingStreet);
                mapper.map(src -> src.getBillingAddress().getCity(),
                        Destination::setBillingCity);
            });
         */

        return ResponseEntity.created(uri).body(userDTO);
    }

    @ApiOperation("수정")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDTO> update(@RequestBody @ApiParam("사용자") User user) {

        userService.update(user);
        UserDTO userDTO = userMapper.toDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @ApiOperation("삭제")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @ApiParam("사용자") Integer id) {
        userService.delete(id);
    }

    @ApiOperation("단건조회 by id")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable @ApiParam("ID") Integer id) {

        User user = userService.findById(id);
        UserDTO userDTO = userMapper.toDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @ApiOperation("단건조회 by 이메일")
    @GetMapping
    public ResponseEntity<UserDTO> getByEmail(@RequestParam @ApiParam("이메일") String email) {
        User user = userService.findByEmail(email);

        String message = messageSource.getMessage("alert.save", new Object[]{"신관영", 1}, LocaleContextHolder.getLocale());
        log.info("{}", message);
        message = messageSource.getMessage("alert.saved", null, LocaleContextHolder.getLocale());
        log.info("{}", message);
        message = messageSource.getMessage("alert.save", new Object[]{"Shin Kwan Young", 1}, Locale.US);
        log.info("{}", message);
        message = messageSource.getMessage("alert.saved", null, Locale.US);
        log.info("{}", message);

        UserDTO userDTO = userMapper.toDTO(user);

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/igp")
    public ResponseEntity<UserDTO> getByEmailFromIgp(@RequestParam @ApiParam("이메일") String email) {
        UserDTO userDto = igpService.findByEmail(email);
        return ResponseEntity.ok(userDto);
    }

}


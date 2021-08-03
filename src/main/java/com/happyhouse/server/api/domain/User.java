package com.happyhouse.server.api.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private Integer id;
    private String email;
    private String password;
    private String roles;
}
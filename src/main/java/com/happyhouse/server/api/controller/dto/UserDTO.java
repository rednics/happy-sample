package com.happyhouse.server.api.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(value="UserDTO", description = "사용자")
public class UserDTO {

    @ApiModelProperty(value="사용자식별자", required=true)
    private Integer userId;

    @ApiModelProperty(value="이메일", example="user@naver.com", required=true)
    private String userEmail;

    @ApiModelProperty(value="비밀번호", example="x343342aAgfdDgf", required=true)
    private String userPassword;

    @ApiModelProperty(value="권한", example="ROLE_USER", required=false)
    private String userRoles;

}
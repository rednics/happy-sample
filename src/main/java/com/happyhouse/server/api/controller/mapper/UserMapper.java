package com.happyhouse.server.api.controller.mapper;

import com.happyhouse.server.api.controller.dto.UserDTO;
import com.happyhouse.server.api.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel ="spring")
public interface UserMapper extends GenericMapper<UserDTO, User> {

    @Mappings({
            @Mapping(target="userId", source="user.id"),
            @Mapping(target="userEmail", source="user.email"),
            @Mapping(target="userPassword", source="user.password"),
            @Mapping(target="userRoles", source="user.roles")
    })
    UserDTO toDTO(User user);

    @Mappings({
            @Mapping(target="id", source="dto.userId"),
            @Mapping(target="email", source="dto.userEmail"),
            @Mapping(target="password", source="dto.userPassword"),
            @Mapping(target="roles", source="dto.userRoles")
    })
    User toDomain(UserDTO dto);

}

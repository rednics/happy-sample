package com.happyhouse.server.api.controller.mapper;

public interface GenericMapper<DTO, DOMAIN> {

    DTO toDTO(DOMAIN domain);
    DOMAIN toDomain(DTO dto);
}

package ru.yandex.mapper;

import org.mapstruct.*;
import ru.yandex.model.User;
import ru.yandex.model.dto.UserCreateDTO;
import ru.yandex.model.dto.UserFullResponseDTO;
import ru.yandex.model.dto.UserResponseDTO;
import ru.yandex.model.dto.UserUpdateDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(UserCreateDTO userCreateDTO);

    User toEntity(UserUpdateDTO userDTO);

    UserCreateDTO toDTO(User user);

    UserResponseDTO toResponseDTO(User user);
    UserFullResponseDTO toFullResponseDTO(User user);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserCreateDTO userCreateDTO, @MappingTarget User user);


}

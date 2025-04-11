package dev.haymon.jwtsecurity.controller.mapper;

import dev.haymon.jwtsecurity.controller.dto.user.UserResponse;
import dev.haymon.jwtsecurity.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {

    @Mapping(target = "fullName", expression = "java(user.fullName())")
    @Mapping(target = "createdAt", source = "createdDate")
    @Mapping(target = "lastModifiedAt", source = "lastModifiedDate")
    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRoles")
    UserResponse toDTO(User user);

    @Named("mapRoles")
    default List<String> mapRoles(List<dev.haymon.jwtsecurity.model.Role> roles) {
        return roles.stream().map(dev.haymon.jwtsecurity.model.Role::getName).toList();
    }
}

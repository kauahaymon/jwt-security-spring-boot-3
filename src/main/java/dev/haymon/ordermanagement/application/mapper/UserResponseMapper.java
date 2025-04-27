package dev.haymon.ordermanagement.application.mapper;

import dev.haymon.ordermanagement.application.dto.user.UserResponse;
import dev.haymon.ordermanagement.domain.model.Role;
import dev.haymon.ordermanagement.domain.model.User;
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
    default List<String> mapRoles(List<Role> roles) {
        return roles.stream().map(Role::getName).toList();
    }
}

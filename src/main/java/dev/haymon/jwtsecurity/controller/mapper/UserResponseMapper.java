package dev.haymon.jwtsecurity.controller.mapper;

import dev.haymon.jwtsecurity.controller.dto.UserResponse;
import dev.haymon.jwtsecurity.model.Role;
import dev.haymon.jwtsecurity.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserResponseMapper {

    public UserResponse toDTO(User user) {
        List<String> roles = user.getRoles().stream().map(Role::getName).toList();
        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.fullName())
                .email(user.getEmail())
                .accountLocked(user.isAccountLocked())
                .enabled(user.isEnabled())
                .createdAt(user.getCreatedDate())
                .lastModifiedAt(user.getLastModifiedDate())
                .roles(roles)
                .build();
    }
}

package dev.haymon.ordermanagement.application.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class UserResponse {

    private Integer id;
    private String fullName;
    private String email;
    private Boolean accountLocked;
    private Boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private List<String> roles;
}

package dev.haymon.ordermanagement.service.validation;

import dev.haymon.ordermanagement.model.User;
import dev.haymon.ordermanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDeletionValidator {

    private final UserRepository userRepository;

    public void validate(Integer id) {
        if (id.equals(getUserAuthenticatedId())) {
            throw new IllegalArgumentException("Você não pode excluir a si mesmo.");
        }
    }

    private Integer getUserAuthenticatedId() {
        return ((User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal()).getId();
    }
}

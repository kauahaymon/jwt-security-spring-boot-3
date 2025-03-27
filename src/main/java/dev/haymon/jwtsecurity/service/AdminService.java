package dev.haymon.jwtsecurity.service;

import dev.haymon.jwtsecurity.model.User;
import dev.haymon.jwtsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    public Page<User> getUsers(Integer pageNumber, Integer pageSize) {
        Pageable userPage = PageRequest.of(pageNumber, pageSize);
        return userRepository.findAll(userPage);
    }
}

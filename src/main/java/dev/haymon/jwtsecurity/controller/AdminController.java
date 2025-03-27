package dev.haymon.jwtsecurity.controller;

import dev.haymon.jwtsecurity.controller.dto.UserResponse;
import dev.haymon.jwtsecurity.model.User;
import dev.haymon.jwtsecurity.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService service;
    private final UserResponseMapper userMapper;

    @GetMapping("/users")
    public ResponseEntity<Page<UserResponse>> listOfUsers(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        Page<User> users = service.getUsers(pageNumber, pageSize);
        Page<UserResponse> userResponses = users.map(userMapper::toDTO);
        return ResponseEntity.ok(userResponses);
    }
}

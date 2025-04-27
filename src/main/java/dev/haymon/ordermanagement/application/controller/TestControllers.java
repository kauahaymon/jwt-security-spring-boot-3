package dev.haymon.ordermanagement.application.controller;
import dev.haymon.ordermanagement.domain.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestControllers {

    @GetMapping("/users")
    public ResponseEntity<?> users() {
        return getGreetings();
    }

    @GetMapping("/admins")
    public ResponseEntity<?> admins() {
        return getGreetings();
    }

    private static ResponseEntity<String> getGreetings() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name;
        if (authentication.getPrincipal() instanceof User user) name = user.fullName();
        else name = authentication.getName();
        var body = "Bem vindo " + name + " !";
        return ResponseEntity.ok(body);
    }
}

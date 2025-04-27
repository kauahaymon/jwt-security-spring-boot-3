package dev.haymon.ordermanagement.application.dto.user;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserUpdateRequest {

    @NotBlank(message = "Nome é obrigatório")
    private String firstname;
    @NotBlank(message = "Sobrenome é obrigatório")
    private String lastname;
    @Email(message = "Formato de e-mail inválido.")
    @NotBlank(message = "Email é obrigatório")
    private String email;
    @NotBlank(message = "Senha é obrigatório")
    @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
    private String password;
    @NotNull(message = "Conta não pode estar nula. Informe se está bloqueada ou não")
    private Boolean accountLocked;
    @NotNull(message = "Conta não pode estar nula. Informe se está habilitada ou não")
    private Boolean enabled;
    @NotEmpty(message = "Usuário não pode ficar sem papel")
    private List<String> roles;
}

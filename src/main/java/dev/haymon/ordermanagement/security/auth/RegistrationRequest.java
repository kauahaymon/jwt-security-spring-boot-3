package dev.haymon.ordermanagement.security.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {

    @NotBlank(message = "Nome é obrigatório")
    private String firstname;
    @NotBlank(message = "Sobrenome é obrigatório")
    private String lastname;
    @Email(message = "Formato de e-mail inválido")
    @NotBlank(message = "Email é obrigatório")
    private String email;
    @NotBlank(message = "Senha é obrigatório")
    @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
    private String password;
}

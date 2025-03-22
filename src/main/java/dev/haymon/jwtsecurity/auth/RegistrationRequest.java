package dev.haymon.jwtsecurity.auth;

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

    private static final String MANDATORY_FIRSTNAME = "Nome é obrigatório";
    private static final String MANDATORY_LASTNAME = "Sobrenome é obrigatório";
    private static final String MANDATORY_EMAIL = "Email é obrigatório";
    private static final String MANDATORY_PASSWORD = "Senha é obrigatório";
    private static final String RULE_PASSWORD = "Senha deve ter no mínimo 8 caracteres";

    @NotBlank(message = MANDATORY_FIRSTNAME)
    private String firstname;
    @NotBlank(message = MANDATORY_LASTNAME)
    private String lastname;
    @Email
    @NotBlank(message = MANDATORY_EMAIL)
    private String email;
    @NotBlank(message = MANDATORY_PASSWORD)
    @Size(min = 8, message = RULE_PASSWORD)
    private String password;
}

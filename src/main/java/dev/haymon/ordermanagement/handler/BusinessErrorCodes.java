package dev.haymon.ordermanagement.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;


@Getter
public enum BusinessErrorCodes {

    NO_CODE(0, HttpStatus.NOT_IMPLEMENTED, "No code"),
    INCORRECT_CURRENT_PASSWORD(300, BAD_REQUEST, "Senha atual incorreta"),
    NEW_PASSWORD_DOES_NOT_MATCH(301, BAD_REQUEST, "Nova senha não confere"),
    ACCOUNT_LOCKED(302, FORBIDDEN, "Conta do usuário está bloqueada"),
    ACCOUNT_DISABLED(303, FORBIDDEN, "Conta do usuário está desabilitada"),
    BAD_CREDENTIALS(304, FORBIDDEN, "Login e / ou senha incorretos")
    ;

    private final int code;
    private final String description;
    private final HttpStatus httpStatus;

    BusinessErrorCodes(int code, HttpStatus httpStatus, String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}

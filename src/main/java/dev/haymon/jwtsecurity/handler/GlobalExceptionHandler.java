    package dev.haymon.jwtsecurity.handler;

    import jakarta.mail.MessagingException;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.authentication.BadCredentialsException;
    import org.springframework.security.authentication.DisabledException;
    import org.springframework.security.authentication.LockedException;
    import org.springframework.web.bind.MethodArgumentNotValidException;
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.bind.annotation.RestControllerAdvice;

    import java.util.HashSet;
    import java.util.Set;

    import static dev.haymon.jwtsecurity.handler.BusinessErrorCodes.*;
    import static org.springframework.http.HttpStatus.*;

    @RestControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(LockedException.class)
        public ResponseEntity<ExceptionResponse> handleException(LockedException e) {
            return ResponseEntity
                    .status(UNAUTHORIZED)
                    .body(
                            ExceptionResponse.builder()
                                    .errorCode(ACCOUNT_LOCKED.getCode())
                                    .description(ACCOUNT_LOCKED.getDescription())
                                    .error(e.getMessage())
                                    .build()
                    );
        }

        @ExceptionHandler(DisabledException.class)
        public ResponseEntity<ExceptionResponse> handleException(DisabledException e) {
            return ResponseEntity
                    .status(UNAUTHORIZED)
                    .body(
                            ExceptionResponse.builder()
                                    .errorCode(ACCOUNT_DISABLED.getCode())
                                    .description(ACCOUNT_DISABLED.getDescription())
                                    .error(e.getMessage())
                                    .build()
                    );
        }

        @ExceptionHandler(BadCredentialsException.class)
        public ResponseEntity<ExceptionResponse> handleException(BadCredentialsException e) {
            return ResponseEntity
                    .status(UNAUTHORIZED)
                    .body(
                            ExceptionResponse.builder()
                                    .errorCode(BAD_CREDENTIALS.getCode())
                                    .description(BAD_CREDENTIALS.getDescription())
                                    .error(BAD_CREDENTIALS.getDescription())
                                    .build()
                    );
        }

        @ExceptionHandler(MessagingException.class)
        public ResponseEntity<ExceptionResponse> handleException(MessagingException e) {
            return ResponseEntity
                    .status(INTERNAL_SERVER_ERROR)
                    .body(
                            ExceptionResponse.builder()
                                    .error(e.getMessage())
                                    .build()
                    );
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException e) {
            Set<String> errors = new HashSet<>();
            e.getBindingResult().getAllErrors()
                    .forEach(error -> {
                        String message = error.getDefaultMessage();
                        errors.add(message);
                    });

            return ResponseEntity
                    .status(BAD_REQUEST)
                    .body(
                            ExceptionResponse.builder()
                                    .validationErrors(errors)
                                    .build()
                    );
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ExceptionResponse> handleException(Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(INTERNAL_SERVER_ERROR)
                    .body(
                            ExceptionResponse.builder()
                                    .description("Ocorreu um erro inesperado. Tente novamente ou entre em contato com o suporte")
                                    .error(e.getMessage())
                                    .build()
                    );
        }
    }

package br.edu.unichristus.biblioteca.Exception;

import br.edu.unichristus.biblioteca.domain.dto.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorDTO> handleApi(ApiException ex) {
        log.error("ApiException: ( )", ex.getMessage(), ex);
        return ResponseEntity.status(ex.getStatus()).
                body(new ErrorDTO(ex.getMessage(), ex.getKey()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleApi(Exception ex) {
        log.error("Exceção não tratada", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(new ErrorDTO("Exceção não tratada", "unichristus.exception"));
    }
}

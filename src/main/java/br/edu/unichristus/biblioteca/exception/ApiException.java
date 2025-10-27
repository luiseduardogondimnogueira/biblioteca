package br.edu.unichristus.biblioteca.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    public final HttpStatus status;
    public final String key;

    public ApiException(HttpStatus status, String key, String message) {
        super(message);
        this.status = status;
        this.key = key;
    }
}

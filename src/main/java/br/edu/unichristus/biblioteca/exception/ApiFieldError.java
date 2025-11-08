package br.edu.unichristus.biblioteca.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiFieldError {

    private String field;

    private String rejectedValue;

    private String message;

}

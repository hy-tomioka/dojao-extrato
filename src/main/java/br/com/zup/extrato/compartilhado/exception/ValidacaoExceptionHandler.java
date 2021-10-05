package br.com.zup.extrato.compartilhado.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ValidacaoExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler
    public List<String> handleConstraintValidationError(ConstraintViolationException exception) {
        return exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }

}

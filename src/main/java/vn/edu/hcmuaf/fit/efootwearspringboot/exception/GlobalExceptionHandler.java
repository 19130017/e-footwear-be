package vn.edu.hcmuaf.fit.efootwearspringboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseError;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity handleBindException(BindException e) {
        StringBuilder stringBuilder = new StringBuilder();
        for (ObjectError error : e.getAllErrors()) {
            stringBuilder.append(error.getDefaultMessage()).append(", ");
        }
        String errorMessage = stringBuilder.substring(0, stringBuilder.length() - 2);
        return ResponseEntity.badRequest().body(HttpResponseError.error(HttpStatus.BAD_REQUEST, errorMessage.toString()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException(NotFoundException e) {
        return ResponseEntity.badRequest().body(HttpResponseError.error(HttpStatus.NOT_FOUND, e.getMessage()));
    }
}

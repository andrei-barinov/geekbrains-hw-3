package ru.geekbrains.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.geekbrains.entity.ErrorEntity;

@ControllerAdvice
public class WebExceptionHandler {
    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorEntity> handlerNotFoundException(NotFoundException e){
       return new ResponseEntity<>(new ErrorEntity(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}

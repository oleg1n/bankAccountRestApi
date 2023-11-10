package com.aston.kupriyanov.bankAccountRestApi.controller;

import com.aston.kupriyanov.bankAccountRestApi.dto.response.IncorrectData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(Exception e){
        IncorrectData incorrectData = new IncorrectData();
        incorrectData.setInfo(e.getMessage());

        return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
    }
}

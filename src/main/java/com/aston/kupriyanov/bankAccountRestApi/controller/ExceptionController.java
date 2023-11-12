package com.aston.kupriyanov.bankAccountRestApi.controller;

import com.aston.kupriyanov.bankAccountRestApi.dto.response.IncorrectDataResponse;
import com.aston.kupriyanov.bankAccountRestApi.exception.CommonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity<IncorrectDataResponse> handleException(CommonNotFoundException e){
        IncorrectDataResponse incorrectDataResponse = new IncorrectDataResponse();
        incorrectDataResponse.setInfo(e.getMessage());

        return new ResponseEntity<>(incorrectDataResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectDataResponse> handleException(Exception e){
        IncorrectDataResponse incorrectDataResponse = new IncorrectDataResponse();
        incorrectDataResponse.setInfo(e.getMessage());

        return new ResponseEntity<>(incorrectDataResponse, HttpStatus.BAD_REQUEST);
    }
}

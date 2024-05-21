package com.ursancristian.bankingsystem.configuration;

import com.ursancristian.bankingsystem.exception.BankAccountNotFoundException;
import com.ursancristian.bankingsystem.exception.ElementNotFoundException;
import com.ursancristian.bankingsystem.exception.FundsValueException;
import com.ursancristian.bankingsystem.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BankAccountNotFoundException.class)
    public ResponseEntity<?> handleBankAccountNotFoundException(BankAccountNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<?> handleElementNotFound(RuntimeException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FundsValueException.class)
    public ResponseEntity<?> handleFundsValueException(FundsValueException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.PAYMENT_REQUIRED);
    }
}

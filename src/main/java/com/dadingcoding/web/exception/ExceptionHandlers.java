package com.dadingcoding.web.exception;

import com.dadingcoding.web.response.ExceptResponse;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

import static com.dadingcoding.web.exception.ErrorCode.*;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlers  {

    @ExceptionHandler({NoSuchEntityException.class})
    public ResponseEntity<ExceptResponse> handleNoSuchEntityException(NoSuchEntityException e){
        log.error("ERROR", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptResponse(400, e.getErrorCode().getMessage(), false));
    }

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<ExceptResponse> handleNoSuchElementException(NoSuchElementException e){
        log.error("ERROR", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptResponse(400, e.getMessage(), false));
    }

    @ExceptionHandler({NoAuthorityToAccessException.class})
    public ResponseEntity<ExceptResponse> handleNoAuthorityToAccessException(NoAuthorityToAccessException e){
        log.error("ERROR", e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ExceptResponse(403, e.getErrorCode().getMessage(), false));
    }

    // Security
    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ExceptResponse> securityException(SecurityException e) {
        log.error("ERROR", e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ExceptResponse(401, e.getMessage(), false));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptResponse> handleUsernameNotFoundException(UsernameNotFoundException e) {
        log.error("ERROR", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptResponse(400, e.getMessage(), false));
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ExceptResponse> handleSignatureException(SignatureException e) {
        log.error("ERROR", e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ExceptResponse(401, e.getMessage(), false));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptResponse> badCredentialsException(BadCredentialsException e) {
        log.error("ERROR", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptResponse(400, WRONG_ID_PASSWORD.getMessage(), false));
    }

    // DEFAULT Exception
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ExceptResponse> handleException(Exception e){
        log.error("ERROR", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptResponse(500, INTERNAL_SERVER_ERROR.getMessage(), false));
    }
}
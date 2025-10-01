package com.pedro.todoListAPI.layers.control.controller;

import com.pedro.todoListAPI.miscelaneous.exceptions.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.URI;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TodoItemNotFoundException.class)
    public ProblemDetail handleBlogPostNotFoundException(TodoItemNotFoundException exception){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );
        detail.setTitle("Blogpost not found.");
        detail.setType(URI.create("about:blank"));
        return detail;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotFoundException(MethodArgumentNotValidException exception){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
        detail.setTitle("Invalid request.");
        detail.setType(URI.create("about:blank"));
        return detail;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail handleArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
        detail.setTitle("Invalid request.");
        detail.setType(URI.create("about:blank"));
        return detail;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleHttpMessageNotReadableException(HttpMessageNotReadableException exception){

        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
        detail.setTitle("Invalid request.");
        detail.setType(URI.create("about:blank"));
        return detail;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ProblemDetail handleHttpMessageNotReadableException(MissingServletRequestParameterException exception){

        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
        detail.setTitle("Invalid request.");
        detail.setType(URI.create("about:blank"));
        return detail;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyDatabaseException.class)
    public ProblemDetail handleEmptyDatabaseException(EmptyDatabaseException exception){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );
        detail.setTitle("Empty DB.");
        detail.setType(URI.create("about:blank"));
        return detail;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PageNotFoundException.class)
    public ProblemDetail handlePageNotFoundException(PageNotFoundException exception){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );
        detail.setTitle("Page not found.");
        detail.setType(URI.create("about:blank"));
        return detail;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TermNotFoundException.class)
    public ProblemDetail handleTermNotFoundException(TermNotFoundException exception){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );
        detail.setTitle("No result found.");
        detail.setType(URI.create("about:blank"));
        return detail;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolationException(ConstraintViolationException exception){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );

        detail.setTitle("Invalid parameter.");
        detail.setType(URI.create("about:blank"));
        return detail;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ProblemDetail handleInternalAuthenticationServiceException(InternalAuthenticationServiceException exception){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
        detail.setTitle("Bad login.");
        detail.setType(URI.create("about:blank"));
        return detail;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail handleBadCredentialsException(BadCredentialsException exception){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
        detail.setTitle("Bad login.");
        detail.setType(URI.create("about:blank"));
        return detail;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentConversionNotSupportedException.class)
    public ProblemDetail handleMethodArgumentConversionNotSupportedException(MethodArgumentConversionNotSupportedException exception){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
        detail.setTitle("Invalid type for parameter.");
        detail.setType(URI.create("about:blank"));
        return detail;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LoginAlreadyInUseException.class)
    public ProblemDetail handleLoginAlreadyInUseException(LoginAlreadyInUseException exception){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
        detail.setTitle("Invalid credentials.");
        detail.setType(URI.create("about:blank"));
        return detail;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PropertyReferenceException.class)
    public ProblemDetail handlePropertyReferenceExceptionHandling(PropertyReferenceException exception){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
        detail.setTitle("Invalid property.");
        detail.setType(URI.create("about:blank"));
        return detail;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgumentException(IllegalArgumentException exception){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
        detail.setTitle("Illegal parameter value.");
        detail.setType(URI.create("about:blank"));
        return detail;
    }


}

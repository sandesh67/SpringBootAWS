package com.amazon.ec2.demo.springbootec2.controller;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class exceptionHandler  {

  @ExceptionHandler(value = {Exception.class})
  public String badResponse(Exception ex) {
    return "bad Response";
  }
}

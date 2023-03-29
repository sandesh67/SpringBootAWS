package com.amazon.ec2.demo.springbootec2.controller;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorControllerEx implements ErrorController {


  @RequestMapping("/error")
  public String handleError() {
    return "error found Sandesh";
  }
}

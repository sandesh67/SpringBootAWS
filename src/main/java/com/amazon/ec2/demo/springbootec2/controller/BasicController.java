package com.amazon.ec2.demo.springbootec2.controller;

import com.amazon.ec2.demo.springbootec2.User;
import com.amazon.ec2.demo.springbootec2.service.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class BasicController {

  @Autowired
  private UserRespository userRespository;

  @RequestMapping( value = "/home", method = RequestMethod.GET)
  public String getHelloWorld() {
    User user = new User();
    user.setName("Sandesh");
    userRespository.save(user);
    return "Hello Sandesh";
  }

  @RequestMapping(value = "/some", method = RequestMethod.GET)
  public String getHello(@RequestParam("par") String param) throws Exception {
    if(param.equals("test")) {
      throw new Exception("test");
    }
    return "Hello User";
  }


}

package com.startup.apiservices.controller;

import java.util.HashMap;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AdminController2 {

  @GetMapping(path = "user")
  public HashMap<String, String> index() {
    // get a successful user login
    String user = ((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    return new HashMap<String, String>() {
      {
        put("user", user);
      }
    };
  }
}

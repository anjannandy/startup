package com.startup.apiservices.controller;

import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AdminController {

  @GetMapping("current-time")
  public String getCurrentTime() {
    return LocalDateTime.now().toString();
  }
}

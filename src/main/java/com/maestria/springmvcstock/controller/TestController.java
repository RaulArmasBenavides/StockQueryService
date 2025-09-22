package com.maestria.springmvcstock.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class TestController {

  @GetMapping("/api/v1/whoami")
  public Map<String, String> whoami(HttpServletRequest req) {
    return Map.of(
        "user",  String.valueOf(req.getHeader("X-User-Id")),
        "roles", String.valueOf(req.getHeader("X-User-Roles"))
    );
  }
}

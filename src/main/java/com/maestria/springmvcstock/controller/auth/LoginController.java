package com.maestria.springmvcstock.controller.auth;
import com.maestria.springmvcstock.application.dto.login.LoginResponse;
import com.maestria.springmvcstock.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maestria.springmvcstock.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String token = userService.authenticate(user.getUsername(), user.getPassword());
        if (token != null) {
            LoginResponse loginResponse = new LoginResponse(user.getUsername(), token);
            return ResponseEntity.ok(loginResponse);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

     @PostMapping("/renewToken")
    public ResponseEntity<?> renewToken(@RequestBody String token) {
        LoginResponse loginResponse = userService.renewToken(token);
        if (loginResponse != null) {
            return ResponseEntity.ok(loginResponse);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("Invalid token");
            return ResponseEntity.status(401).body(errorResponse);
        }
    }


    
}

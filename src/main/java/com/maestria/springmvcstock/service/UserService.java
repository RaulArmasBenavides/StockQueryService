package com.maestria.springmvcstock.service;

 
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.maestria.springmvcstock.application.dto.login.LoginResponse;
import com.maestria.springmvcstock.model.User;
import com.maestria.springmvcstock.repository.UserRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Value("${SECRET_KEY}")
    private String secretKey;
    public String authenticate(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (new BCryptPasswordEncoder().matches(password, user.getPassword())) {
                return generateToken(user);
            }
        }
        return null;
    } 

    private String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 864_000_000)) // 10 days
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public LoginResponse renewToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
            String username = claims.getSubject();
            Optional<User> userOptional = userRepository.findByUsername(username);
            if (userOptional.isPresent()) {
                String newToken = generateToken(userOptional.get());
                return new LoginResponse(username, newToken);
            }
        } catch (Exception e) {
            // Handle exception
        }
        return null;
    }


    public User save(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }
}
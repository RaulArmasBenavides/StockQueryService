package com.maestria.springmvcstock.service;

 
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.maestria.springmvcstock.model.User;
import com.maestria.springmvcstock.repository.UserRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final String SECRET_KEY = "YmFzZTY0ZW5jb2RlZFNlY3JldEtleVNlY3JldEtleVNlY3JldEtleVNlY3JldEtleVNlY3JldEtleVNlY3JldEtleQ==";

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
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public User save(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }
}
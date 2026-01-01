package com.sk.travel.controller;

import com.sk.travel.model.LoginRequest;
import com.sk.travel.model.User;
import com.sk.travel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
//        Optional<User> userOpt = Optional.ofNullable(userRepository.findByEmail(request.getEmail()));
//        if (userOpt.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email");
//        }
//
//        User user = userOpt.get();
//        // Check password (plain or encoded)
//        if (!user.getPassword().equals(request.getPassword())) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
//        }
//
//        // Return role in response
//        Map<String, String> response = new HashMap<>();
//        response.put("role", user.getRole().name());
//        response.put("email", user.getEmail());
//        return ResponseEntity.ok(response);
//    }
//}

package com.sk.travel.auth;

import com.sk.travel.model.LoginRequest;
import com.sk.travel.model.User;
import com.sk.travel.repository.UserRepository;
import com.sk.travel.security.JwtUtil;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        System.out.println("Login Request: "+ request.getEmail()+" "+request.getPassword());
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        if(!user.getPassword().equals(request.getPassword())){
            System.out.println("user Request: "+ user.getPassword()+" "+request.getPassword());
            throw new RuntimeException("Invalid Credentials");
        }
        String token=jwtUtil.generateToken(user.getEmail(),user.getRole().name());
        return ResponseEntity.ok(
                Map.of(
                        "token", token,
                        "role", user.getRole().name()
                )
        );
    }
}

package com.sk.travel.controller;

import com.sk.travel.model.User;
import com.sk.travel.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@NoArgsConstructor
public class CustController {

    @Autowired
    private UserService userService;

//
//    @PostMapping("/login")
//    public User login(@RequestBody User user) {
//        System.out.println(user.getPhonenumber());
//        return userService.getUser(user.getPhonenumber());
//    }
//
//    @PostMapping("/register")
//    public User register(@RequestBody User user) {
//        return userService.createUser(user);
//    }
//    @DeleteMapping("/delete")
//    public String delete(@RequestBody User user) {
//        userService.deleteUser(user.getId());
//        return "Deleted successfully!";
//    }
//
//    @GetMapping("/users")
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }

    @GetMapping("user/{email}")
    public User getUserByEmail(@PathVariable("email") String email) {
        System.out.println(email);
        return userService.getUserByEmail(email);
    }
}

package com.sk.travel.controller;

import com.sk.travel.model.User;
import com.sk.travel.repository.UserRepository;
import com.sk.travel.service.PhotoService;
import com.sk.travel.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/")
@NoArgsConstructor
public class CustController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhotoService photoService;
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

    @PostMapping("/{id}/upload-photo")
    public ResponseEntity<String> uploadPhoto(@PathVariable Long id,
                                              @RequestParam("file") MultipartFile file) throws IOException {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        String photoId = photoService.uploadPhoto(file);
        user.setProfilePhotoId(photoId);
        userRepository.save(user);
        return ResponseEntity.ok(photoId);
    }

    @GetMapping("/{id}/photo")
    public ResponseEntity<byte[]> getPhoto(@PathVariable Long id) throws IOException {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getProfilePhotoId() == null) {
            throw new RuntimeException("No photo uploaded");
        }
        byte[] image = photoService.getPhoto(user.getProfilePhotoId());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }


}

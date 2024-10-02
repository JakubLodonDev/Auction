package com.auction.dev.controller;

import com.auction.dev.request.UpdateUserRequest;
import com.auction.dev.model.User;
import com.auction.dev.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://127.0.0.1:5500")
@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<User> getUserDetails() {
        User user = userService.getUserDetails();
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<User> updateUserDetails(@RequestBody UpdateUserRequest updateUserRequest) {
        User updatedUser = userService.updateUserDetails(updateUserRequest);
        return ResponseEntity.ok(updatedUser);
    }
}

package com.camposeduardo.generator.controller;

import com.camposeduardo.generator.entities.UserProfile;
import com.camposeduardo.generator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserProfile> getUserProfileInfo(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(userService.getUserProfileInfo(token));
    }
}

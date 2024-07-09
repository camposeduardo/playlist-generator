package com.camposeduardo.generator.controller;

import com.camposeduardo.generator.entities.AccessTokenResponse;
import com.camposeduardo.generator.service.LoginService;
import com.camposeduardo.generator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @GetMapping("/authorize")
    public ResponseEntity<Map<String, String>> authorize() throws NoSuchAlgorithmException, InvalidKeyException {
        return ResponseEntity.ok().body(loginService.redirectToSpotifyAuthorization());
    }

    @GetMapping("/token")
    public ResponseEntity<AccessTokenResponse> getAccessToken(@RequestHeader("code") String code,
                                                              @RequestHeader("verifier") String verifier)  {
        return ResponseEntity.ok().body(userService.requestAccessToken(code, verifier));
    }
}

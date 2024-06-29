package com.camposeduardo.generator.spotify.controller;

import com.camposeduardo.generator.spotify.dto.AccessTokenResponse;
import com.camposeduardo.generator.spotify.dto.SpotifyAuthFlowResponse;
import com.camposeduardo.generator.spotify.service.LoginService;
import com.camposeduardo.generator.spotify.service.SpotifyTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private SpotifyTokenService spotifyTokenService;

    @GetMapping("/authorize")
    public ResponseEntity<SpotifyAuthFlowResponse> authorize() throws NoSuchAlgorithmException, InvalidKeyException {
        return ResponseEntity.ok().body(loginService.redirectToSpotifyAuthorization());
    }

    @GetMapping("/token")
    public ResponseEntity<AccessTokenResponse> getAccessToken(@RequestHeader("code") String code,
                                                              @RequestHeader("verifier") String verifier)  {
        return ResponseEntity.ok().body(spotifyTokenService.requestAccessToken(code, verifier));
    }
}

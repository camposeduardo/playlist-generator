package com.camposeduardo.generator.spotify.service;

import com.camposeduardo.generator.spotify.dto.AccessTokenResponse;
import com.camposeduardo.generator.spotify.dto.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class SpotifyTokenService {

    @Autowired
    private RestTemplate restTemplate;

    public AccessTokenResponse requestAccessToken(String code, String verifier) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String clientId = System.getenv("CLIENT_ID");

        String urlTemplate = UriComponentsBuilder.fromHttpUrl("https://accounts.spotify.com/api/token")
                .queryParam("client_id", clientId)
                .queryParam("grant_type", "authorization_code")
                .queryParam("code", code)
                .queryParam("redirect_uri", "http://localhost:4200")
                .queryParam("code_verifier", verifier)
                .toUriString();

        ResponseEntity<AccessTokenResponse> token = restTemplate.exchange(
                urlTemplate, HttpMethod.POST, entity,
                AccessTokenResponse.class);

        return token.getBody();
    }
}

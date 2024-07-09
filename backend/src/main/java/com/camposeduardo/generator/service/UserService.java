package com.camposeduardo.generator.service;

import com.camposeduardo.generator.entities.AccessTokenResponse;
import com.camposeduardo.generator.entities.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    public UserProfile getUserProfileInfo(String token) {

        String url = "https://api.spotify.com/v1/me";

        String spotifyToken = token.substring(7);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(spotifyToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<UserProfile> response = restTemplate.exchange(
                url, HttpMethod.GET, entity,
                UserProfile.class);
        return response.getBody();
    }

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

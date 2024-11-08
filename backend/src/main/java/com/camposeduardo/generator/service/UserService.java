package com.camposeduardo.generator.service;

import com.camposeduardo.generator.entities.AccessTokenResponse;
import com.camposeduardo.generator.entities.UserProfile;
import com.camposeduardo.generator.exceptions.InvalidSpofityTokenException;
import com.camposeduardo.generator.exceptions.InvalidSpotifyResponseBodyException;
import com.camposeduardo.generator.exceptions.SpotifyApiErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TokenService tokenService;

    public UserProfile getUserProfileInfo(String token) {

        if (tokenService.isTokenEmpty(token)) {
            throw new InvalidSpofityTokenException();
        }

        String url = "https://api.spotify.com/v1/me";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UserProfile profile = null;

        try {
            ResponseEntity<UserProfile> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity,
                    UserProfile.class);

            profile = response.getBody();

            if (profile == null || profile.getId() == null
                    || profile.getDisplayName() == null) {
                throw new InvalidSpotifyResponseBodyException("Failed to find user profile");
            }

        } catch (RestClientException e) {
            throw new SpotifyApiErrorException();
        }

        return profile;
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

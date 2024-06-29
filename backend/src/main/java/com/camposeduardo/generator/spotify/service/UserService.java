package com.camposeduardo.generator.spotify.service;

import com.camposeduardo.generator.spotify.dto.AccessTokenResponse;
import com.camposeduardo.generator.spotify.dto.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        System.out.println(response.getBody());
        return response.getBody();
    }
}

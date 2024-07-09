package com.camposeduardo.generator.service;

import com.camposeduardo.generator.entities.RecommendationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RecommendationService {
    @Autowired
    private RestTemplate restTemplate;

    private String url = "https://api.spotify.com/v1/recommendations?seed_artists=%s&seed_genres=%s&limit=100";

    public RecommendationResponse generateRecommendation(String artistId, String genre,
                                                         String token) {
        HttpHeaders headers = new HttpHeaders();
        String spotifyToken = token.substring(7);
        headers.setBearerAuth(spotifyToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String fullUrl = String.format(url, artistId, genre);
        ResponseEntity<RecommendationResponse> response = restTemplate.exchange(fullUrl, HttpMethod.GET, entity,
                RecommendationResponse.class);
        return response.getBody();
    }
}

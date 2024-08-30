package com.camposeduardo.generator.service;

import com.camposeduardo.generator.entities.RecommendationResponse;
import com.camposeduardo.generator.exceptions.InvalidSpofityTokenException;
import com.camposeduardo.generator.exceptions.SpotifyApiErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class RecommendationService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TokenService tokenService;

    public RecommendationResponse generateRecommendation(String artistId, String genre,
                                                         String token) {

        if (tokenService.isTokenEmpty(token)) {
            throw new InvalidSpofityTokenException();
        }
        
        String url = "https://api.spotify.com/v1/recommendations?seed_artists=%s&seed_genres=%s&limit=100";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String fullUrl = String.format(url, artistId, genre);

        RecommendationResponse tracks = null;
        
        try {
            ResponseEntity<RecommendationResponse> response = restTemplate.exchange(fullUrl, HttpMethod.GET, entity,
                    RecommendationResponse.class);

            tracks = response.getBody();

            if (tracks == null || tracks.getTracks() == null) {
                // custom exception
            }

        } catch (RestClientException er) {
            throw new SpotifyApiErrorException();
        }
        
        return tracks;
    }
}

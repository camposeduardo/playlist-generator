package com.camposeduardo.generator.spotify.service;

import com.camposeduardo.generator.spotify.dto.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SearchService {

    @Autowired
    private RestTemplate restTemplate;

    private String url = "https://api.spotify.com/v1/search?q=%s&type=artist&limit=%d";

    public SearchResponse search(String artist, String token) {

        HttpHeaders headers = new HttpHeaders();
        String spotifyToken = token.substring(7);
        headers.setBearerAuth(spotifyToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String fullUrl = String.format(url, artist, 10);
        ResponseEntity<SearchResponse> response = restTemplate.exchange(fullUrl, HttpMethod.GET, entity,
                SearchResponse.class);

        return response.getBody();
    }


}

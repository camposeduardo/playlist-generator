package com.camposeduardo.generator.service;

import com.camposeduardo.generator.entities.SearchResponse;
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

        if (response.getBody().getArtists() == null) {
            // throw some custom exception
        }

        SearchResponse searchResponse = response.getBody();

        // https://www.baeldung.com/java-concurrentmodificationexception
        searchResponse.getArtists().getItems().removeIf(i -> i.getImages() == null || i.getGenres() == null);

        return searchResponse;
    }


}

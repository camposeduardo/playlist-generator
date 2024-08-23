package com.camposeduardo.generator.service;

import com.camposeduardo.generator.entities.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class SearchService {

    @Autowired
    private RestTemplate restTemplate;


    public SearchResponse search(String artist, String token) {

        String url = "https://api.spotify.com/v1/search?q=%s&type=artist&limit=%d";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String fullUrl = String.format(url, artist, 10);

        SearchResponse searchResponse = null;

        try {
            ResponseEntity<SearchResponse> response = restTemplate.exchange(fullUrl, HttpMethod.GET, entity,
                    SearchResponse.class);

            searchResponse = response.getBody();

            if (searchResponse == null || searchResponse.getArtists() == null) {
                // custom exception
            }

            // https://www.baeldung.com/java-concurrentmodificationexception
            searchResponse.getArtists().getItems().removeIf(i -> i.getImages().isEmpty()
                    || i.getGenres().isEmpty());

        } catch (RestClientException e) {
            // custom exception
        }


        return searchResponse;
    }


}

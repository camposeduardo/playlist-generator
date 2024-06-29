package com.camposeduardo.generator.spotify.controller;

import com.camposeduardo.generator.spotify.dto.RecommendationResponse;
import com.camposeduardo.generator.spotify.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/recommendation")
    public ResponseEntity<RecommendationResponse> generateRecommendantion(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                         @RequestParam String artistId, @RequestParam String genre) {
        return ResponseEntity.ok().body(recommendationService.generateRecommendation(artistId, genre,token));
    }
}

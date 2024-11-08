package com.camposeduardo.generator.controller;

import com.camposeduardo.generator.entities.RecommendationResponse;
import com.camposeduardo.generator.service.RecommendationService;
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
        return ResponseEntity.ok().body(recommendationService.generateRecommendation(artistId, genre, token));
    }
}

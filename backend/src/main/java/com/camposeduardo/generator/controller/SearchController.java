package com.camposeduardo.generator.controller;

import com.camposeduardo.generator.entities.SearchResponse;
import com.camposeduardo.generator.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public ResponseEntity<SearchResponse> search(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestParam String artist) {
        return ResponseEntity.ok().body(searchService.search(artist,token));
    }

}

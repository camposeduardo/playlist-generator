package com.camposeduardo.generator.controller;

import com.camposeduardo.generator.entities.CreatePlaylistRequest;
import com.camposeduardo.generator.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;


    @PostMapping("/playlist/generate")
    public ResponseEntity<String> generatePlaylist(@RequestHeader("Authorization") String token,
                                                   @RequestParam String userId,
                                                   @RequestBody CreatePlaylistRequest playlist
                                                   ) {
        return ResponseEntity.ok().body(playlistService.createPlaylist(token, userId, playlist));
    }
}

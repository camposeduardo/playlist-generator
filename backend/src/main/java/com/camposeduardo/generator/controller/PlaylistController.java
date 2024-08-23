package com.camposeduardo.generator.controller;

import com.camposeduardo.generator.entities.CreatePlaylistRequest;
import com.camposeduardo.generator.entities.CreatePlaylistResponse;
import com.camposeduardo.generator.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;


    @PostMapping("/playlist/generate")
    public ResponseEntity<CreatePlaylistResponse> generatePlaylist(@RequestHeader("Authorization") String token,
                                                                   @RequestParam String userId,
                                                                   @RequestBody CreatePlaylistRequest playlist
                                                   ) {
        return ResponseEntity.ok().body(playlistService.generatePlaylist(token, userId, playlist));
    }
}

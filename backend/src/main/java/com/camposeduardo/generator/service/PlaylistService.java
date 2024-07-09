package com.camposeduardo.generator.service;

import com.camposeduardo.generator.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class PlaylistService {

    @Autowired
    private RestTemplate restTemplate;

    public String createPlaylist(String token, String userId, CreatePlaylistRequest playlist)  {
        String url = "https://api.spotify.com/v1/users/%s/playlists";

        HttpHeaders headers = new HttpHeaders();
        String spotifyToken = token.substring(7);
        headers.setBearerAuth(spotifyToken);

        HttpEntity<Playlist> entity = new HttpEntity<>(playlist.getPlaylist(),headers);

        String fullUrl = String.format(url, userId);
        ResponseEntity<CreatePlaylistResponse> response = restTemplate.postForEntity(fullUrl, entity,
                CreatePlaylistResponse.class);

        String addMusic = addMusicsToPlaylist(playlist.getTracks(), token, response.getBody().getId());

        if (addMusic.equals("Playlist created")) {
            return "";
        }

        return null;
    }

    public String addMusicsToPlaylist(List<Track> musics, String token, String playlistId) {

        String url = "https://api.spotify.com/v1/playlists/%s/tracks";

        HttpHeaders headers = new HttpHeaders();
        String spotifyToken = token.substring(7);
        headers.setBearerAuth(spotifyToken);

        List<String> tracksUri = musics.stream().map(Track::getUri).toList();

        HttpEntity<List<String>> entity = new HttpEntity<>(tracksUri, headers);

        String fullUrl = String.format(url, playlistId);
        ResponseEntity<PlaylistTrackResponse> response = restTemplate.postForEntity(fullUrl, entity,
                PlaylistTrackResponse.class);

        if (response.getBody().getSnapshotId().isBlank()) {
            return null;
        }

        return "Playlist created";

    }

}

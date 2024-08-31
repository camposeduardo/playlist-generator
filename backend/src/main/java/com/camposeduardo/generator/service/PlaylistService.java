package com.camposeduardo.generator.service;

import com.camposeduardo.generator.entities.*;
import com.camposeduardo.generator.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TokenService tokenService;

    public CreatePlaylistResponse generatePlaylist(String token, String userId, CreatePlaylistRequest playlist)  {

        if (tokenService.isTokenEmpty(token)) {
            throw new InvalidSpofityTokenException();
        }

        if (userId == null || userId.isBlank()) {
           throw new UserIdInvalidException();
        }

        String url = "https://api.spotify.com/v1/users/%s/playlists";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        if (isPlaylistEmpty(playlist.getPlaylist())) {
            throw new InvalidPlaylistException();
        }

        HttpEntity<Playlist> entity = new HttpEntity<>(playlist.getPlaylist(),headers);

        String fullUrl = String.format(url, userId);
        CreatePlaylistResponse createPlaylistResponse = null;

        try {
            ResponseEntity<CreatePlaylistResponse> response = restTemplate.postForEntity(fullUrl, entity,
                    CreatePlaylistResponse.class);

            createPlaylistResponse = response.getBody();

            if (createPlaylistResponse == null || createPlaylistResponse.getId() == null) {
                throw new InvalidSpotifyResponseBodyException("Playlist creation failed.");
            }

        } catch (RestClientException er) {
            throw new SpotifyApiErrorException();
        }

        addTracksToPlaylist(playlist.getTracks(), token, createPlaylistResponse.getId());

        return createPlaylistResponse;
    }

    public void addTracksToPlaylist(List<Track> musics, String token, String playlistId) {

        if (tokenService.isTokenEmpty(token)) {
            throw new InvalidSpofityTokenException();
        }

        String url = "https://api.spotify.com/v1/playlists/%s/tracks";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        List<String> tracksUri = musics.stream().map(Track::getUri).toList();

        HttpEntity<List<String>> entity = new HttpEntity<>(tracksUri, headers);

        String fullUrl = String.format(url, playlistId);

        try {
            ResponseEntity<PlaylistTrackResponse> response = restTemplate.postForEntity(fullUrl, entity,
                    PlaylistTrackResponse.class);

            PlaylistTrackResponse body = response.getBody();

            if (body == null || body.getSnapshotId() == null) {
                throw new InvalidSpotifyResponseBodyException("Failed to add tracks.");
            }

        } catch (RestClientException er) {
            throw new SpotifyApiErrorException();
        }

    }
    
    public boolean isPlaylistEmpty(Playlist playlist) {
        return playlist == null || playlist.getName() == null || playlist.getName().isBlank();
    }


}

package com.camposeduardo.generator.spotify.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class SpotifyAuthFlowResponse {

    private String url;
    private String verifier;
}

package com.camposeduardo.generator.spotify.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class Track {

    private List<Artist> artists;

    @JsonProperty("duration_ms")
    private String duration;

    private String id;

    private Album album;

    private String name;

    @JsonProperty("preview_url")
    private String previewUrl;

    private String uri;
}

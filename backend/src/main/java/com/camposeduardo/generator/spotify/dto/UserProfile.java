package com.camposeduardo.generator.spotify.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class UserProfile {

    @JsonProperty("display_name")
    private String displayName;
    private String email;
    private String country;
    @JsonProperty("followers")
    private Follower followers;
    private List<Image> images;
}

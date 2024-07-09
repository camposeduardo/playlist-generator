package com.camposeduardo.generator.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class UserProfile {

    private String id;
    @JsonProperty("display_name")
    private String displayName;
    private String email;
    private String country;
    @JsonProperty("followers")
    private Follower followers;
    private List<Image> images;
}

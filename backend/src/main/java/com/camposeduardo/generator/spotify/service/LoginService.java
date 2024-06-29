package com.camposeduardo.generator.spotify.service;

import com.camposeduardo.generator.spotify.dto.AccessTokenResponse;
import com.camposeduardo.generator.spotify.dto.SpotifyAuthFlowResponse;
import com.camposeduardo.generator.spotify.dto.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class LoginService {

    @Autowired
    private static final SecureRandom RANDOM = new SecureRandom();

    public SpotifyAuthFlowResponse redirectToSpotifyAuthorization() throws NoSuchAlgorithmException {
        String verifier = this.generateCodeVerifier();
        String challenge = this.generateCodeChallenge(verifier);

        String clientId = System.getenv("CLIENT_ID");
        System.out.println(clientId);

        StringBuilder spotifyUrl = new StringBuilder("https://accounts.spotify.com/authorize?");
        spotifyUrl.append("client_id=");
        spotifyUrl.append(clientId);
        spotifyUrl.append("&response_type=code&");
        spotifyUrl.append("&redirect_uri=http://localhost:4200");
        spotifyUrl.append("&scope=user-read-private user-read-email playlist-modify-public playlist-modify-private");
        spotifyUrl.append("&code_challenge_method=S256");
        spotifyUrl.append("&code_challenge=");
        spotifyUrl.append(challenge);

        return new SpotifyAuthFlowResponse(spotifyUrl.toString(), verifier);
    }

    public String generateCodeVerifier() {
        StringBuilder result = new StringBuilder(128);
        String possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < 128; i++) {
            int index = RANDOM.nextInt(possible.length());
            result.append(possible.charAt(index));
        }
        return result.toString();
    }

    public String generateCodeChallenge(String codeVerifier) throws NoSuchAlgorithmException {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(codeVerifier.getBytes());
        return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);

    }
}

package com.camposeduardo.generator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private static final SecureRandom RANDOM = new SecureRandom();

    public Map<String, String> redirectToSpotifyAuthorization() throws NoSuchAlgorithmException {
        String verifier = this.generateCodeVerifier();
        String challenge = this.generateCodeChallenge(verifier);

        String clientId = System.getenv("CLIENT_ID");

        StringBuilder spotifyUrl = new StringBuilder("https://accounts.spotify.com/authorize?");
        spotifyUrl.append("client_id=");
        spotifyUrl.append(clientId);
        spotifyUrl.append("&response_type=code");
        spotifyUrl.append("&redirect_uri=http://localhost:4200");
        spotifyUrl.append("&scope=user-read-private user-read-email playlist-modify-public playlist-modify-private");
        spotifyUrl.append("&code_challenge_method=S256");
        spotifyUrl.append("&code_challenge=");
        spotifyUrl.append(challenge);

        Map<String, String> spotifyAuthFlowParams = Map.of("url", spotifyUrl.toString(),
                                                            "verifier", verifier);
        return spotifyAuthFlowParams;
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

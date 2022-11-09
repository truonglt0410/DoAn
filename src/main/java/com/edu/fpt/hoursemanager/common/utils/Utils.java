package com.edu.fpt.hoursemanager.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.edu.fpt.hoursemanager.config.oauth2.OAuth2UserCustom;
import com.edu.fpt.hoursemanager.exceptions.HouseManagerExceptions;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.*;
import java.util.stream.Collectors;

public class Utils {
    public static Map<String, String> generateAccessTokenAndRefreshToken(String secret, int expiration, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .withIssuedAt(new Date())
                .withClaim("roles",user.getAuthorities().stream().map(GrantedAuthority
                        ::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration*3))
                .withIssuedAt(new Date())
                .withClaim("roles",user.getAuthorities().stream().map(GrantedAuthority
                        ::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token",refresh_token);
        Const.TOKENS.remove(user.getUsername());
        Const.TOKENS.put(user.getUsername(),access_token);
        return tokens;
    }

    public static Map<String, String> generateAccessTokenAndRefreshTokenOAuth(String secret, int expiration, Authentication authentication){
        OAuth2UserCustom oAuth2User = (OAuth2UserCustom) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        String access_token = JWT.create()
                .withSubject(oAuth2User.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .withIssuedAt(new Date())
                .withClaim("roles",new ArrayList<>())
                .sign(algorithm);
        String refresh_token = JWT.create()
                .withSubject(oAuth2User.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration*3))
                .withIssuedAt(new Date())
                .withClaim("roles",new ArrayList<>())
                .sign(algorithm);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token",refresh_token);
        Const.TOKENS.remove(oAuth2User.getName());
        Const.TOKENS.put(oAuth2User.getName(),access_token);
        return tokens;
    }

    public static String generatePassword() {
        String result = "";
        result = RandomStringUtils.random(8, true, true);
        return result;
    }
    public static Integer generateOTP() {
        Random random = new Random();
        return random.nextInt(900000)+100000;
    }

}

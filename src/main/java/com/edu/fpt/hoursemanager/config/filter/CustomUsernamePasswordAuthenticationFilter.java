package com.edu.fpt.hoursemanager.config.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.common.utils.Utils;
import com.edu.fpt.hoursemanager.exceptions.HouseManagerExceptions;
import com.edu.fpt.hoursemanager.management.account.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private String jwtSecret;

    private int jwtExpirationMs;

    private final AuthenticationManager authenticationManager;

    public CustomUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager,String jwtSecret, int jwtExpirationMs) {
        this.authenticationManager = authenticationManager;
        this.jwtSecret = jwtSecret;
        this.jwtExpirationMs = jwtExpirationMs;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        log.info("Username is {}",email); log.info("Password is {}",password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,password);
        return  authenticationManager.authenticate(authenticationToken);
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), ResponseModels.success(Utils.generateAccessTokenAndRefreshToken(this.jwtSecret,this.jwtExpirationMs,authentication)));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), ResponseModels.unauthorized("Signin error"));
    }
}

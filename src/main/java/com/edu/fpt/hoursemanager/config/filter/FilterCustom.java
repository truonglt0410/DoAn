package com.edu.fpt.hoursemanager.config.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.edu.fpt.hoursemanager.HourseManagerApplication;
import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.common.utils.Const;
import com.edu.fpt.hoursemanager.common.utils.Utils;
import com.edu.fpt.hoursemanager.exceptions.HouseManagerExceptions;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;

@Component
@Slf4j
public class FilterCustom extends OncePerRequestFilter {
    @Value("${hoursemanager.app.jwtSecret}")
    private String jwtSecret;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("doFilterInternal in CustomAuthorizationFilter");
        log.info("request.getRequestURI() {}" , request.getRequestURI());
        log.info("request.getServletPath() {}" , request.getServletPath());
        log.info("request.getContextPath() {}" , request.getContextPath());

        if(request.getRequestURI().equals("/api/authentication/google")){
            response.sendRedirect(request.getContextPath()+"/oauth2/authorization/google");
        }
        else if(request.getRequestURI().equals(request.getContextPath()+"/")){
            response.sendRedirect(request.getContextPath()+"/authentication/wellcome");
        }else{
            String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                try{
                    String token = authorizationHeader.substring("Bearer ".length());
                    Algorithm algorithm = Algorithm.HMAC256(this.jwtSecret.getBytes());
                    JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = jwtVerifier.verify(token);
                    String username = decodedJWT.getSubject();
                    String tokenOld = Const.TOKENS.get(username);
                    if(tokenOld == null || tokenOld.isEmpty() || !tokenOld.equals(token)){
                        throw new HouseManagerExceptions("Token Old");
                    }
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    stream(roles).forEach(role -> {
                        authorities.add(new SimpleGrantedAuthority(role));
                    });
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(username,
                                    null,authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request,response);
                }catch (Exception exception){
                    log.info("Error loggin in :{}",exception.getMessage());
                    Map<String,String> error = new HashMap<>();
                    error.put("message",exception.getMessage());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), ResponseModels.unauthorized(error));
                }
            }else{
                filterChain.doFilter(request,response);
            }
        }
    }
}

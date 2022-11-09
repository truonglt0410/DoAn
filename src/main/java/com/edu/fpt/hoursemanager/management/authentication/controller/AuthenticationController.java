package com.edu.fpt.hoursemanager.management.authentication.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.common.utils.Const;
import com.edu.fpt.hoursemanager.exceptions.HouseManagerExceptions;
import com.edu.fpt.hoursemanager.management.authentication.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/wellcome")
    public ResponseEntity<?> wellcome() {
        return ResponseModels.success("true");
    }

    @GetMapping("/forgot")
    public ResponseEntity<?> forgotPassword(@RequestParam("email") String email) {
        return authenticationService.generateOTP(email);
    }

    @GetMapping("/otp")
    public ResponseEntity<?> checkOTP(@RequestParam("otp") Integer otp, @RequestParam("email") String email) {
        return authenticationService.checkOTP(otp,email);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam("email") String email) {
        return authenticationService.logout(email);
    }

}

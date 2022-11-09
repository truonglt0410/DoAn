package com.edu.fpt.hoursemanager.management.authentication.service;

import com.edu.fpt.hoursemanager.HourseManagerApplication;
import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<ResponseModels> generateOTP(String email);

    ResponseEntity<ResponseModels> checkOTP(Integer otp,String email);

    ResponseEntity<ResponseModels> logout(String email);
}

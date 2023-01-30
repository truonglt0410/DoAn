package com.edu.fpt.hoursemanager.management.authentication.service;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.authentication.model.request.ChangePasswordRequest;
import com.edu.fpt.hoursemanager.management.authentication.model.request.RegisterEmployeeRequest;
import com.edu.fpt.hoursemanager.management.authentication.model.request.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<ResponseModels> generateOTP(String email);

    ResponseEntity<ResponseModels> checkOTP(Integer otp,String email);

    ResponseEntity<ResponseModels> createPassword(String key, String email, String password);

    ResponseEntity<ResponseModels> logout(String email);

    ResponseEntity<ResponseModels> register(RegisterRequest registerRequest);

    ResponseEntity<ResponseModels> registerEmployee(RegisterEmployeeRequest registerEmployeeRequest);

    ResponseEntity<ResponseModels> changePassword(ChangePasswordRequest changePasswordRequest);

    ResponseEntity<ResponseModels> wellcome(String email);


}

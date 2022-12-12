package com.edu.fpt.hoursemanager.management.authentication.controller;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.account.repository.AccountRepository;
import com.edu.fpt.hoursemanager.management.account.repository.RoleRepository;
import com.edu.fpt.hoursemanager.management.account.service.AccountService;
import com.edu.fpt.hoursemanager.management.authentication.model.request.ChangePasswordRequest;
import com.edu.fpt.hoursemanager.management.authentication.model.request.RegisterEmployeeRequest;
import com.edu.fpt.hoursemanager.management.authentication.model.request.RegisterRequest;
import com.edu.fpt.hoursemanager.management.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private RoleRepository roleRepository;

    private AccountService accountService;

    private AccountRepository accountRepository;

    @GetMapping("/wellcome")
    public ResponseEntity<?> wellcome(@RequestParam("email") String email) {
        return authenticationService.wellcome(email);
    }

    @GetMapping("/forgot")
    public ResponseEntity<?> forgotPassword(@RequestParam("email") String email) {
        return authenticationService.generateOTP(email);
    }

    @GetMapping("/otp")
    public ResponseEntity<?> checkOTP(@RequestParam("otp") Integer otp, @RequestParam("email") String email) {
        return authenticationService.checkOTP(otp,email);
    }


    @GetMapping("/create-password")
    public ResponseEntity<?> checkCreatePass(@RequestParam("key") String key, @RequestParam("email") String email,
                                             @RequestParam("password") String password) {
        return authenticationService.createPassword(key, email, password);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam("email") String email) {
        return authenticationService.logout(email);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseModels> register(@RequestBody RegisterRequest registerRequest){
        return authenticationService.register(registerRequest);
    }

    @PostMapping("/register-employee")
    public ResponseEntity<ResponseModels> registerEmployee(@RequestBody RegisterEmployeeRequest registerEmployeeRequest){
        return authenticationService.registerEmployee(registerEmployeeRequest);
    }

    @PostMapping("/change-password")
    public ResponseEntity<ResponseModels> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        return authenticationService.changePassword(changePasswordRequest);
    }
}

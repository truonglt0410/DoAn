package com.edu.fpt.hoursemanager.config.oauth2;

import com.edu.fpt.hoursemanager.common.enums.ProviderAccount;
import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.common.utils.Const;
import com.edu.fpt.hoursemanager.common.utils.Utils;
import com.edu.fpt.hoursemanager.management.account.entity.Account;
import com.edu.fpt.hoursemanager.management.account.entity.Role;
import com.edu.fpt.hoursemanager.management.account.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private AccountService accountService;

    @Value("${hoursemanager.app.jwtSecret}")
    private String jwtSecret;

    @Value("${hoursemanager.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        Map<String, String> tokens = Utils.generateAccessTokenAndRefreshTokenOAuth(this.jwtSecret,this.jwtExpirationMs,authentication);
        OAuth2UserCustom oAuth2User = (OAuth2UserCustom) authentication.getPrincipal();
        Account account = new Account();
        account.setCreatedBy(oAuth2User.getAttribute("email"));
        account.setModifiedBy(oAuth2User.getAttribute("email"));
        account.setEmail(oAuth2User.getAttribute("email"));
        account.setPassword(Utils.generatePassword());
        account.setAuthProvider(ProviderAccount.GOOGLE);
        Role role = new Role();
        role.setId(1L);
        account.setRoles(List.of(role));
        Account accountExits = accountService.getAccount(oAuth2User.getAttribute("email"));
        if(accountExits==null){
            accountService.saveAccount(account);
        }
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Const.ACCOUNT_TOKENS.put(oAuth2User.getAttribute("email"),tokens);
        new ObjectMapper().writeValue(response.getOutputStream(), ResponseModels.success(tokens));
    }

}
package com.edu.fpt.hoursemanager.management.authentication.service.impl;

import com.edu.fpt.hoursemanager.common.entity.AccountLoginCommon;
import com.edu.fpt.hoursemanager.common.enums.ProviderAccount;
import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.common.utils.Const;
import com.edu.fpt.hoursemanager.common.utils.Utils;
import com.edu.fpt.hoursemanager.exceptions.HouseManagerExceptions;
import com.edu.fpt.hoursemanager.management.account.entity.Account;
import com.edu.fpt.hoursemanager.management.account.entity.Role;
import com.edu.fpt.hoursemanager.management.account.repository.AccountRepository;
import com.edu.fpt.hoursemanager.management.account.repository.RoleRepository;
import com.edu.fpt.hoursemanager.management.account.service.AccountService;
import com.edu.fpt.hoursemanager.management.authentication.model.request.ChangePasswordRequest;
import com.edu.fpt.hoursemanager.management.authentication.model.request.RegisterEmployeeRequest;
import com.edu.fpt.hoursemanager.management.authentication.model.request.RegisterRequest;
import com.edu.fpt.hoursemanager.management.authentication.service.AuthenticationService;
import com.edu.fpt.hoursemanager.management.contact.entity.Contact;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    @Value("${hoursemanager.app.otp}")
    private Integer expired;

    @Value("${hoursemanager.app.subjectmail}")
    private String subjectMail;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public ResponseEntity<ResponseModels> generateOTP(String email) {
        Account accountExits = accountService.getAccount(email);
        if(accountExits==null){
            return ResponseModels.notFound("Error email");
        }else{
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Const.OTPS.remove(email);
                        Integer otp = Utils.generateOTP();
                        Const.OTPS.put(email, otp);
                        log.info("---- Send mail otp: {}",otp);
                        sendMail(email,otp);
                        Thread.sleep(expired);
                        Const.OTPS.remove(email);
                        log.info("---- Delete otp: {}",otp);
                    } catch (Exception e) {
                        log.info("---- OTP error: {}",e.getMessage());
                    }
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
            return ResponseModels.success();
        }
    }

    @Override
    public ResponseEntity<ResponseModels> checkOTP(Integer otp, String email) {
        Account accountExits = accountService.getAccount(email);
        if(accountExits==null){
            return ResponseModels.notFound("Error email");
        }else{
            Integer otpOld = Const.OTPS.get(email);
            if(otpOld!=null){
                if(otpOld.intValue()!=otp.intValue()){
                    return ResponseModels.conflict();
                }else{
                    Const.FORGOTPASS.remove(email);
                    String forgotpass = passwordEncoder.encode(Utils.generateForgotPassword());
                    Const.FORGOTPASS.put(email, forgotpass);
                    HashMap<String, String> objectResponse = new HashMap<>();
                    objectResponse.put("key",forgotpass);
                    return ResponseModels.success(objectResponse);
                }
            }else{
                return ResponseModels.timeOut();
            }
        }
    }

    @Override
    public ResponseEntity<ResponseModels> createPassword(String key, String email, String password) {
        Account accountExits = accountService.getAccount(email);
        if(accountExits==null){
            return ResponseModels.notFound("Error email");
        }else{
            String keyServer = Const.FORGOTPASS.get(email);
            if(keyServer != null ){
                if(!keyServer.equals(key)){
                    return ResponseModels.conflict();
                }else{
                    Const.FORGOTPASS.remove(email);
                    accountExits.setPassword(password);
                    accountService.saveAccount(accountExits);
                    return ResponseModels.success();
                }
            }else{
                return ResponseModels.error("Email don't forgot password");
            }
        }
    }

    @Override
    public ResponseEntity<ResponseModels> logout(String email) {
        Account accountExits = accountService.getAccount(email);
        if(accountExits==null){
            return ResponseModels.notFound("Not Found");
        }else{
            Const.TOKENS.remove(email);
            return ResponseModels.success();
        }
    }

    private void sendMail(String email, Integer OTP) throws HouseManagerExceptions {
        try{
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(this.buildEmail(OTP), true);
            helper.setTo(email);
            helper.setSubject(subjectMail);
            emailSender.send(mimeMessage);
        }catch (MessagingException e){
            throw new HouseManagerExceptions(e.getMessage());
        }
    }

    private String buildEmail(Integer OTP) {
        //************************************
        // Function build content mail
        //************************************
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        calendar.add(Calendar.SECOND, this.expired/1000);
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        String dateTimeAfterFormat = dateTimeFormat.format(calendar.getTime());

        StringBuilder sql = new StringBuilder("");
        sql.append("    <div style=\"max-width: 680px; \">\n" +
                "        <table width=\"100%\">\n" +
                "            <tr>\n" +
                "                   <h1>OTP: "+OTP+"</h1>"    +
                "            </tr>\n" +
//                "            <tr>\n" +
//                "                   <h1>Expired: "+dateTimeAfterFormat+"</h1>"    +
//                "            </tr>\n" +
                "        </table>\n" +
                "    </div>");
        return sql.toString();
    }

    public ResponseEntity<ResponseModels> register(RegisterRequest registerRequest){
        Account account = new Account();
        try{
            if(StringUtils.isNotEmpty(registerRequest.getEmail())
                    && StringUtils.isNotEmpty(registerRequest.getPassword())){
                Account checkExist = accountRepository.findByUsername(registerRequest.getEmail());

                if(checkExist == null){
                    List<Role> roles = new ArrayList<>();
                    account.setEmail(registerRequest.getEmail());
                    account.setPassword(registerRequest.getPassword());
                    for(String s : registerRequest.getRoles()){
                        roles.add(roleRepository.findByCode(s));
                    }
                    account.setRoles(roles);
                    account.setAuthProvider(ProviderAccount.DATABASE);
                    account.setContact(new Contact());
                    accountService.saveAccount(account);
                }else {
                    return ResponseModels.success("Account is Exist.");
                }
            }else {
                return ResponseModels.success("Account or Password is Null.");
            }
        }catch (Exception e){
            return ResponseModels.error("Register Error : " + e.getMessage());
        }
        return ResponseModels.created("Register Success.");
    }

    @Override
    public ResponseEntity<ResponseModels> registerEmployee(RegisterEmployeeRequest registerEmployeeRequest) {
        Account account = new Account();
        Contact contact = new Contact();
        try{
            if(StringUtils.isNotEmpty(registerEmployeeRequest.getEmail())
                    && StringUtils.isNotEmpty(registerEmployeeRequest.getPassword())){
                Account checkExist = accountRepository.findByUsername(registerEmployeeRequest.getEmail());
                contact.setAddress(registerEmployeeRequest.getAddress());
                if(StringUtils.isNotEmpty(registerEmployeeRequest.getDob())){
                    contact.setDob(SIMPLE_DATE_FORMAT.parse(registerEmployeeRequest.getDob()));
                }
                contact.setFullName(registerEmployeeRequest.getFullName());
                contact.setGender(registerEmployeeRequest.getGender());
                contact.setPhone(registerEmployeeRequest.getPhone());
                contact.setType(registerEmployeeRequest.getType());
                contact.setCreatedBy(registerEmployeeRequest.getEmail());
                contact.setCreatedDate(LocalDate.now());
                if(checkExist == null){
                    List<Role> roles = new ArrayList<>();

                    account.setEmail(registerEmployeeRequest.getEmail());
                    account.setPassword(registerEmployeeRequest.getPassword());
                    for(String s : registerEmployeeRequest.getRoles()){
                        roles.add(roleRepository.findByCode(s));
                    }
                    account.setRoles(roles);
                    account.setAuthProvider(ProviderAccount.DATABASE);
                    account.setContact(contact);
                    account.setCreatedBy(registerEmployeeRequest.getEmail());
                    account.setCreatedDate(LocalDate.now());
                    accountService.saveAccount(account);
                }else {
                    return ResponseModels.success("Account employee is Exist.");
                }
            }else {
                return ResponseModels.success("Account employee or Password is Null.");
            }
        }catch (Exception e){
            return ResponseModels.error("Register Error : " + e.getMessage());
        }
        return ResponseModels.created("Register employee Success.");
    }

    @Override
    public ResponseEntity<ResponseModels> changePassword(ChangePasswordRequest changePasswordRequest){
        AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
        Account account;
        try{
            account = accountService.getAccount(accountLoginCommon.getUserName());
            if(passwordEncoder.matches(changePasswordRequest.getOldPassword(), account.getPassword()) ){
                account.setPassword(changePasswordRequest.getNewPassword());
                accountService.saveAccount(account);
            }else{
                return ResponseModels.success("Change Password Fail. Old Password Is Wrong");
            }
        }catch (Exception e){
            return ResponseModels.error("Change Password Error : " + e.getMessage());
        }
        return ResponseModels.success("Change Password Success.");
    }

    @Override
    public ResponseEntity<ResponseModels> wellcome(String email) {
        Account accountExits = accountService.getAccount(email);
        if(accountExits==null){
            return ResponseModels.notFound("Error email");
        }else{
            Map<String, String> tokens = Const.ACCOUNT_TOKENS.get(email);
            if(tokens != null ){
                Const.ACCOUNT_TOKENS.remove(email);
                return ResponseModels.success(tokens);
            }else{
                return ResponseModels.error("Email don't have token by login google");
            }
        }
    }
}

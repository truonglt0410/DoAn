package com.edu.fpt.hoursemanager.management.authentication.service.impl;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.common.utils.Const;
import com.edu.fpt.hoursemanager.common.utils.Utils;
import com.edu.fpt.hoursemanager.exceptions.HouseManagerExceptions;
import com.edu.fpt.hoursemanager.management.account.entity.Account;
import com.edu.fpt.hoursemanager.management.account.service.AccountService;
import com.edu.fpt.hoursemanager.management.authentication.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AccountService accountService;

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
                if(otpOld!=otp){
                    return ResponseModels.conflict();
                }else{
                    return ResponseModels.success();
                }
            }else{
                return ResponseModels.timeOut();
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
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.add(Calendar.SECOND, this.expired/1000);
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        String dateTimeAfterFormat = dateTimeFormat.format(calendar.getTime());

        StringBuilder sql = new StringBuilder("");
        sql.append("    <div style=\"max-width: 680px; \">\n" +
                "        <table width=\"100%\">\n" +
                "            <tr>\n" +
                "                   <h1>OTP: "+OTP+"</h1>"    +
                "            </tr>\n" +
                "            <tr>\n" +
                "                   <h1>Expired: "+dateTimeAfterFormat+"</h1>"    +
                "            </tr>\n" +
                "        </table>\n" +
                "    </div>");
        return sql.toString();
    }
}

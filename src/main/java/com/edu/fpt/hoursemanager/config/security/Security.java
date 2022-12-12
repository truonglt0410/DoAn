package com.edu.fpt.hoursemanager.config.security;

import com.edu.fpt.hoursemanager.config.filter.CustomUsernamePasswordAuthenticationFilter;
import com.edu.fpt.hoursemanager.config.filter.FilterCustom;
import com.edu.fpt.hoursemanager.config.oauth2.OAuth2LoginSuccessHandler;
import com.edu.fpt.hoursemanager.config.oauth2.OAuth2UserServiceCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Slf4j
public class Security extends WebSecurityConfigurerAdapter {

    @Value("${hoursemanager.app.jwtSecret}")
    private String jwtSecret;


    @Value("${hoursemanager.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private FilterCustom filterCustom;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //************************************
        // config AuthenticationManagerBuilder với password là BCryptPasswordEncoder
        //************************************
        log.info("configure(AuthenticationManagerBuilder auth) in SecurityConfig");
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/oauth2/**"
            // other public endpoints of your API may be appended to this array
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //************************************
        // cấu hình phân quyền theo đường dẫn
        //************************************
        log.info("configure(HttpSecurity http) in SecurityConfig");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        CustomUsernamePasswordAuthenticationFilter authenticationFilter =
                new CustomUsernamePasswordAuthenticationFilter(authenticationManagerBean(),jwtSecret,jwtExpirationMs);
        authenticationFilter.setFilterProcessesUrl("/signin");

        http.authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/authentication/**").permitAll()
                .antMatchers("/admin/**").hasAnyAuthority("CN")
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/signin").permitAll()
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(oAuth2UserServiceCustom)
                .and()
                .successHandler(oAuth2LoginSuccessHandler)
                .and()
                .addFilter(authenticationFilter)
                .addFilterBefore(filterCustom, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        log.info("authenticationManagerBean in SecurityConfig");
        return super.authenticationManagerBean();
    }

    @Autowired
    private OAuth2UserServiceCustom oAuth2UserServiceCustom;

    @Autowired
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

}

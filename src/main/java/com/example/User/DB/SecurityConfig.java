package com.example.User.DB;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


import org.springframework.context.annotation.Bean;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;





@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers().authenticated() // Require authentication for this endpoint
                .anyRequest().permitAll() // Allow other endpoints to be accessed without authentication
                .and().httpBasic();
    }


    @Bean
      public SecurityFilterChain securityFilterChain  (HttpSecurity http) throws Exception {
       return http.csrf().disable()
                .authorizeRequests()
                .antMatchers().authenticated() // Require authentication for this endpoint
                .anyRequest().permitAll() // Allow other endpoints to be accessed without authentication
                .and()
               .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }




}





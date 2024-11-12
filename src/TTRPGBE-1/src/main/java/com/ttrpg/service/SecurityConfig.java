package com.ttrpg.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;


@Configuration
public class SecurityConfig {

  
 
  
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())  
            
            .authorizeRequests(auth -> auth
                .anyRequest().permitAll() 
            )
            .logout(logout -> logout
                    .logoutUrl("/api/auth/logout") // Set the logout URL
                    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)) // Send 200 OK on successful logout
                    
                    .deleteCookies("JSESSIONID") // Delete session cookie on logout
                )
            .build();
    }
}
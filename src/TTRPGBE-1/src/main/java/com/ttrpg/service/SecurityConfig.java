package com.ttrpg.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	
    	http.csrf(Customizer -> Customizer.disable());
    	http.authorizeHttpRequests(request -> 
    	request.requestMatchers("/h2-console/**", "/login**" ,"/","/signup"
    			,"/error**", 
                "/h2-console/**",
                "/login/oauth2/code/google-login",       
                "/signup/oauth2/code/google-signup",
                
                "/api/auth/google-login/oauth2/code/google").permitAll().
    	anyRequest().authenticated()).oauth2Login(oa2 -> oa2.defaultSuccessUrl("/",true));
    	http.formLogin(Customizer.withDefaults());
    	http.headers(headers -> headers.frameOptions(fo -> fo.sameOrigin()));
    	http.httpBasic(Customizer.withDefaults());
    	
     /*   http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/login**", "/error**", "/h2-console/**").permitAll() // Public endpoints
                .anyRequest().authenticated() // All other endpoints require authentication
            )
            .oauth2Login(oauth2 -> oauth2
                .loginPage("/login") // Custom login page (optional)
                .defaultSuccessUrl("/home") // Redirect after successful login
            )
            .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity
            .headers(headers -> headers.frameOptions().disable()); // Disable X-Frame-Options for H2 console*/
        return http.build();
    }
}










// import org.springframework.context.annotation.Configuration;

/*import org.springframework.context.annotation.Bean;
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
            
            .logout(logout -> logout
                    .logoutUrl("/api/auth/logout") // Set the logout URL
                    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)) // Send 200 OK on successful logout
                    
                    .deleteCookies("JSESSIONID") // Delete session cookie on logout
                )
            .build();
    }
    
}
*/
package com.ttrpg.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
@Configuration
public class WebConfig  implements WebMvcConfigurer{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/{path:[^\\.]*}")
                .setViewName("forward:/index.html");


    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")    //svi putevi koji započinju s api su ignorirani osim ako su pozvani od frontenda
                .allowedOrigins("http://localhost:5000",  "https://nat20mappers-frontend.onrender.com") //domene
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true); //da se mogu slati headeri i cookies
    }
}

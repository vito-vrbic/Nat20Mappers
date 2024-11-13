package com.ttrpg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import com.ttrpg.model.Korisnik;
import com.ttrpg.repository.KorisnikRepository;
import com.ttrpg.service.IgraService;
import com.ttrpg.service.KorisnikService;






@SpringBootApplication
public class Ttrpgbe1Application {
	
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4173")  // Frontend URL
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
                        .allowedHeaders("*")  // Allowed headers
                        .allowCredentials(true);  // Allow credentials (cookies)
            }
        };
	}
	


	public static void main(String[] args) {
		
		 ApplicationContext context = SpringApplication.run(Ttrpgbe1Application.class, args);

	        // Retrieve the KorisnikService bean from the context
	        KorisnikService korisnikService = context.getBean(KorisnikService.class);
		
	        IgraService  igraService= context.getBean(IgraService.class);
		
		
		
		
		
		korisnikService.sDataLoader();
		igraService.s2DataLoader();
		igraService.s3DataLoader();
		igraService.s4DataLoader();
	}

}

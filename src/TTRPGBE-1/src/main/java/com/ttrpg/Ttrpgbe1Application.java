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

    // Postavke CORS-a za omogućavanje komunikacije s frontendom
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4173") // URL frontenda
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

    public static void main(String[] args) {
        // Pokretanje aplikacije
        ApplicationContext context = SpringApplication.run(Ttrpgbe1Application.class, args);

        // Inicijalizacija podataka
        KorisnikService korisnikService = context.getBean(KorisnikService.class);
        IgraService igraService = context.getBean(IgraService.class);

        korisnikService.sDataLoader();
        igraService.s2DataLoader();
        igraService.s3DataLoader();
        igraService.s4DataLoader();
    }
}

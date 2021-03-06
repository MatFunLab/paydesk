package btb.hr.vinoljupci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootApplication

public class TestApplication {

	public static void main(String[] args) {
		
		
		SpringApplication.run(TestApplication.class, args);
		

		}
	
	@SuppressWarnings("deprecation")
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            	 registry.addMapping("/**").allowedOrigins("http://localhost:4200");
            }
        };
    }
			
}

package mx.com.backend.asesores.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by.
 */
@Configuration
public class ApplicationConfig {

  /**
   * Model mapper.
   *
   * @return the model mapper
   */
  @Bean
  ModelMapper modelMapper() {
    return new ModelMapper();
  }

  /**
   * Cors configurer.
   * 
   * Configuracion para que todos nuestros apiRest puedan ser consumidos desde
   * cualquier origen.
   *
   * @return the web mvc configurer
   */
  @Bean
  WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
      }
    };
  }
}

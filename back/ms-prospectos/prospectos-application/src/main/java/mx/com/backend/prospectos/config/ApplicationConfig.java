package mx.com.backend.prospectos.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by 
 */
@Configuration
public class ApplicationConfig {
    @Bean
    ModelMapper modelMapper() {
      ModelMapper modelMapper = new ModelMapper();
      modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
      return modelMapper;
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

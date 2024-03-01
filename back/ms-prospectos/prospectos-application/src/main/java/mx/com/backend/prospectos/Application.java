package mx.com.backend.prospectos;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan(basePackages = "mx.com.backend.prospectos")
@EntityScan("mx.com.backend.prospectos.model")
@EnableJpaRepositories("mx.com.backend.prospectos.repository")
@EnableWebMvc
public class Application {

    public static void main(String[] args) {
        final SpringApplication springApplication =
            new SpringApplication(Application.class);
        // ir is being added here for local run ONLY , spring profiles should be be run time parameters when run spring boot jar
        springApplication.setDefaultProperties(Collections.singletonMap("spring.profiles.default","local"));
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }

}

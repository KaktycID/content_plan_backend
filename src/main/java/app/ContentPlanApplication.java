package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@EnableJpaRepositories("content")
@EntityScan("content")
@ComponentScan("content")
@SpringBootApplication
public class ContentPlanApplication {

    public static void main(String[] args) { SpringApplication.run(ContentPlanApplication.class, args);

    }
}

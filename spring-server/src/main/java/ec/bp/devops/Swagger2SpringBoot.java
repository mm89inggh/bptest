package ec.bp.devops;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "ec.bp.devops", "ec.bp.devops.api" , "ec.bp.devops.config"})
public class Swagger2SpringBoot{

    public static void main(String[] args) {
        SpringApplication.run(Swagger2SpringBoot.class, args);
    }
}

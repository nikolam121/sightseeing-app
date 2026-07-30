package hr.tis.academy.sightseeingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"hr.tis.academy.sightseeingapp.mapper"})
public class SightSeeingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SightSeeingAppApplication.class, args);
    }

}

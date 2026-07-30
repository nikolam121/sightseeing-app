package hr.tis.academy.sightseeingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"hr.tis.academy.sightseeingapp.controller",
        "hr.tis.academy.sightseeingapp.dto",
        "hr.tis.academy.sightseeingapp.enums",
        "hr.tis.academy.sightseeingapp.mapper",
        "hr.tis.academy.sightseeingapp.model",
        "hr.tis.academy.sightseeingapp.repository",
        "hr.tis.academy.sightseeingapp.repository.exception",
        "hr.tis.academy.sightseeingapp.service",
        "hr.tis.academy.sightseeingapp.service.impl"
})
public class SightSeeingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SightSeeingAppApplication.class, args);
    }

}

package org.ft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.ft.model.CollegeStudent;

@SpringBootApplication
public class MvcTestingExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcTestingExampleApplication.class, args);
    }

    @Bean(name = "collegeStudent")
    @Scope(value = "prototype")
    CollegeStudent getCollegeStudent() {
        return new CollegeStudent();
    }

}

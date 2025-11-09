package com.example.demo.baseapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BaseAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseAppApplication.class, args);
        try {
            com.example.demo.baseapp.genrator.CodeGenerator.main(new String[]{"src/main/resources/input/employee.json"});
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
    }

}


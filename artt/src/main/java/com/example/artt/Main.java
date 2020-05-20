package com.example.artt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import service.SteeringCommitteeService;
import ui.Console;
import ui.commands.AddSteeringCommittee;
import domain.*;

@EntityScan("domain")
@SpringBootApplication
public class Main {
    public static void main(String[] args)
    {

        SpringApplication.run(Main.class, args);
        Console console=new Console();
        console.show();
    }
}

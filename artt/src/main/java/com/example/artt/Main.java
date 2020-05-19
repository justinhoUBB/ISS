package com.example.artt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.SteeringCommitteeService;
import ui.Console;
import ui.commands.AddSteeringCommittee;

@SpringBootApplication
public class Main {
    public static void main(String[] args)
    {
        SpringApplication.run(Main.class, args);
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext("config");
        SteeringCommitteeService steeringCommitteeService=applicationContext.getBean(SteeringCommitteeService.class);

        Console console=new Console();
        console.addCommand(new AddSteeringCommittee("1","Add SteeringCommittee",steeringCommitteeService));




        console.show();
    }
}

package ubb.project.iss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@SpringBootApplication
public class ProjectApplication {


    public static void main(String[] args) {

        SpringApplication.run(ProjectApplication.class, args);
    }
}
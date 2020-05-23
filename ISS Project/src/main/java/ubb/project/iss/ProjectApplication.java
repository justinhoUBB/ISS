package ubb.project.iss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ubb.project.iss.domain.Conference;

import java.util.Objects;

@SpringBootApplication
public class ProjectApplication {


    public static void main(String[] args) {

        SpringApplication.run(ProjectApplication.class, args);
        final String URLpapers = "http://localhost:8080/api/papers";
        final String URLconferences = "http://localhost:8080/api/conferences";
        final String URLsteerings = "http://localhost:8080/api/steerings";
        final String URLusers = "http://localhost:8080/api/users";

        RestTemplate restTemplate=new RestTemplate();
        //print all
        ResponseEntity<Conference[]> response =
                restTemplate.getForEntity(
                        URLconferences,
                        Conference[].class);
        Conference[] conferences = response.getBody();
        System.out.println(conferences.length);

        for (Conference conference : Objects.requireNonNull(conferences)) {
            System.out.println(conference);
        }
    }
}

package ubb.project.iss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ubb.project.iss.domain.Conference;
import ubb.project.iss.domain.Paper;
import ubb.project.iss.domain.SteeringCommittee;
import ubb.project.iss.domain.User;

import java.util.List;
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
        //save element
        Paper p=new Paper("aaa",2l);
        restTemplate.postForObject(URLpapers,p,Paper.class);
        //print all
        ResponseEntity<Paper[]> response =
                restTemplate.getForEntity(
                        URLpapers,
                        Paper[].class);
        Paper[] clients = response.getBody();
        System.out.println(clients.length);

        for (Paper client : Objects.requireNonNull(clients)) {
            System.out.println(client);
        }

        //find by id
        Paper myClient = restTemplate.getForObject(URLpapers + "/{id}", Paper.class, (long) Integer.parseInt("1"));
        System.out.println(myClient);
    }
}

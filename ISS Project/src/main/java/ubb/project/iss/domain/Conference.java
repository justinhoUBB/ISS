package ubb.project.iss.domain;

import lombok.*;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Conference extends BaseEntity<Long>
{
    private String description; // chestia cu call for papers
    private String topics; // astea o sa fie delimitate prin spatiu
    private LocalDate starting_date;
    private LocalDate paper_deadline;
    private LocalDate bid_deadline;
    private int number_of_rooms;
    private int number_of_seats_per_room;
}


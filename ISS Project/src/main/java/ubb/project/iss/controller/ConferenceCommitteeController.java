package ubb.project.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ubb.project.iss.domain.Conference;
import ubb.project.iss.domain.ConferenceCommittee;
import ubb.project.iss.response.CommitteeMemberResponse;
import ubb.project.iss.service.ConferenceCommitteeService;
import ubb.project.iss.service.ConferenceService;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge=3600, allowedHeaders = "*", allowCredentials = "true")
public class ConferenceCommitteeController {

    @Autowired
    private ConferenceCommitteeService conferenceCommitteeService;

    @RequestMapping(value = "/conference_committee", method = RequestMethod.GET)
    List<ConferenceCommittee> getConferences() {
        return conferenceCommitteeService.getAll();
    }

    @RequestMapping(value = "/conference_committee", method = RequestMethod.POST)
    ConferenceCommittee save(@RequestBody ConferenceCommittee conference) {
        return conferenceCommitteeService.save(conference);
    }

    @RequestMapping(value = "/conference_committee/{conference_id}", method = RequestMethod.POST)
    CommitteeMemberResponse getUserById(@PathVariable Long conference_id, @RequestBody ConferenceCommittee conferenceCommittee) {

       if (conferenceCommitteeService.getUserById(conference_id,  conferenceCommittee).isEmpty()) {
           return  CommitteeMemberResponse.builder()
                   .status(false)
                   .conferenceCommittee(conferenceCommittee)
                   .build();
       }
       else{
            return  CommitteeMemberResponse.builder()
                    .status(true)
                    .conferenceCommittee(conferenceCommittee)
                    .build();
        }
    }
  //  @RequestMapping(value = "/conferences_committee/{id}", method = RequestMethod.GET)
   // ConferenceCommittee getUserById(@PathVariable Long id) {
  //      return conferenceCommitteeService.getById(id);
   // }


}
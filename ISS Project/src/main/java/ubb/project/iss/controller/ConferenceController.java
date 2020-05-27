package ubb.project.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ubb.project.iss.domain.Conference;
import ubb.project.iss.response.GetBidResponse;
import ubb.project.iss.service.ConferenceService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge=3600, allowedHeaders = "*", allowCredentials = "true")
public class ConferenceController {
    @Autowired
    private ConferenceService conferenceService;

    @RequestMapping(value = "/conferences", method = RequestMethod.GET)
    List<Conference> getConferences() {
        return conferenceService.getAll();
    }

    @RequestMapping(value = "/conferences", method = RequestMethod.POST)
    Conference save(@RequestBody Conference conference) {
        return conferenceService.save(conference);
    }

    @RequestMapping(value = "/conferences_bid/{id}", method = RequestMethod.GET)
    GetBidResponse getBidDeadlineById(@PathVariable Long id, Conference conference) {

        return  GetBidResponse.builder().bid_deadline(conferenceService.getBidById(id)).conference(conference).build();
    }

    @RequestMapping(value = "/conferences/{id}", method = RequestMethod.GET)
   Conference getById(@PathVariable Long id) {
        return conferenceService.getById(id);
    }

    @RequestMapping(value = "/conferences/{id}", method = RequestMethod.PUT)
    Conference update(@PathVariable Long id,@RequestBody Conference conference) {
        return conferenceService.update(conference,id);
    }
    
    @RequestMapping(value = "/settle_bid/{id}", method = RequestMethod.PUT)
    void settleBid(@PathVariable Long id) {
        conferenceService.settleBids(id);
    }
}
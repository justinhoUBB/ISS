package ubb.project.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ubb.project.iss.domain.Conference;
import ubb.project.iss.domain.Paper;
import ubb.project.iss.domain.SteeringCommittee;
import ubb.project.iss.service.ServiceInterface;

import java.util.List;

@RestController
public class ConferenceController {
    @Autowired
    private ServiceInterface<Conference> conferenceService;

    @RequestMapping(value = "/conferences", method = RequestMethod.GET)
    List<Conference> getConferences() {
        return conferenceService.getAll();
    }

    @RequestMapping(value = "/conferences", method = RequestMethod.POST)
    Conference save(@RequestBody Conference conference) {
        return conferenceService.save(conference);
    }

    @RequestMapping(value = "/conferences/{id}", method = RequestMethod.GET)
    Conference getById(@PathVariable Long id) {
        return conferenceService.getById(id);
    }
}

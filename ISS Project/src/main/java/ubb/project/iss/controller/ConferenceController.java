package iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import iss.domain.Conference;
import iss.service.ConferenceService;

import java.util.List;

@RestController
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

    @RequestMapping(value = "/conferences/{id}", method = RequestMethod.GET)
    Conference getById(@PathVariable Long id) {
        return conferenceService.getById(id);
    }
}
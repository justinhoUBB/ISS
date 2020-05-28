package ubb.project.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ubb.project.iss.domain.Repartition;
import ubb.project.iss.service.RepartitionService;
import ubb.project.iss.service.SectionService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge=3600,allowedHeaders = "*", allowCredentials = "true")
public class RepartitionController {
    @Autowired
    private RepartitionService repartitionService;
    @Autowired
    private SectionService sectionService;

    @RequestMapping(value = "/repartitions/{id}", method = RequestMethod.PUT)
    void createRepartitions(@PathVariable Long id)
    {
        sectionService.createRepartitions(id);
    }

    @RequestMapping(value = "/repartitions/{id}", method = RequestMethod.GET)
    List<Repartition> getRepartitionsOfConference(@PathVariable Long id)
    {
        return repartitionService.findByConferenceID(id);
    }
}

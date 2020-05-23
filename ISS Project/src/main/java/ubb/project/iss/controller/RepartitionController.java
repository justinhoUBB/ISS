package ubb.project.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ubb.project.iss.domain.Repartition;
import ubb.project.iss.service.RepartitionService;
import ubb.project.iss.service.SectionService;

import java.util.List;

@RestController
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

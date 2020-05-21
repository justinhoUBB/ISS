package ubb.project.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ubb.project.iss.domain.Repartition;
import ubb.project.iss.domain.User;
import ubb.project.iss.service.RepartitionService;

import java.util.List;

@RestController
public class RepartitionController {
    @Autowired
    private RepartitionService repartitionService;

    @RequestMapping(value = "/repartitions/{id}", method = RequestMethod.GET)
    List<Repartition> getRepartitionsOfConference(@PathVariable Long id)
    {
        return repartitionService.findByConferenceID(id);
    }
}

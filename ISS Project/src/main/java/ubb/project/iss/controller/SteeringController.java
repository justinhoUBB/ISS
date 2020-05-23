package iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import iss.domain.SteeringCommittee;
import iss.service.SteeringService;

import java.util.List;
@RestController
public class SteeringController {
    @Autowired
    private SteeringService steeringCommitteeService;

    @RequestMapping(value = "/steerings", method = RequestMethod.GET)
    List<SteeringCommittee> getPapers() {
        return steeringCommitteeService.getAll();
    }

    @RequestMapping(value = "/steerings", method = RequestMethod.POST)
    SteeringCommittee save(@RequestBody SteeringCommittee steeringCommittee) {
        return steeringCommitteeService.save(steeringCommittee);
    }
    @RequestMapping(value = "/steerings/{id}", method = RequestMethod.GET)
    SteeringCommittee getById(@PathVariable Long id) {
        return steeringCommitteeService.getById(id);
    }
}
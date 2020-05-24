package ubb.project.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ubb.project.iss.domain.Section;
import ubb.project.iss.service.SectionService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge=3600,allowedHeaders = "*", allowCredentials = "true")
public class SectionController {
    @Autowired
    SectionService  sectionService;
    @RequestMapping(value = "/sections/{id}", method = RequestMethod.GET)
    List<Section> getSectionOfConference(@PathVariable Long id)
    {
        return sectionService.getAllByConferenceID(id);
    }
    @RequestMapping(value = "/sections/{id}", method = RequestMethod.PUT)
    void createSections(@PathVariable Long id)
    {
        sectionService.createSections(id);
    }
}

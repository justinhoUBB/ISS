package ubb.project.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ubb.project.iss.domain.Section;
import ubb.project.iss.service.SectionService;

import java.util.List;

@RestController
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

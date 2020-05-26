package ubb.project.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ubb.project.iss.domain.Paper;
import ubb.project.iss.service.PaperService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge=3600,allowedHeaders = "*", allowCredentials = "true")
public class PaperController {
    @Autowired
    private PaperService paperService;

    @RequestMapping(value = "/papers", method = RequestMethod.GET)
    List<Paper> getPapers() {
        return paperService.getAll();
    }

    @RequestMapping(value = "/papers", method = RequestMethod.POST)
    Paper save(@RequestBody Paper paper) {
        return paperService.save(paper);
    }
    @RequestMapping(value = "/papers/{id}", method = RequestMethod.GET)
    Paper getById(@PathVariable Long id) {
        return paperService.getById(id);
    }

    @RequestMapping(value ="/papers/{id}", method = RequestMethod.PUT)
    Paper paperReviewed(@PathVariable Long id){
        return paperService.paperReviewed(id);
        }

    @RequestMapping(value="/papers/{id}", method = RequestMethod.GET)
    List<Paper> getPapersAtConference(@PathVariable Long id) { return paperService.getPapersAtConference(id); }
}

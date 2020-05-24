package ubb.project.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ubb.project.iss.domain.*;
import ubb.project.iss.service.PaperSubmissionService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge=3600,allowedHeaders = "*", allowCredentials = "true")
public class PaperSubmissionController {
    @Autowired
    private PaperSubmissionService paperSubmissionService;

    @RequestMapping(value = "/submissions", method = RequestMethod.GET)
    List<PaperSubmission> getSubmissions()
    {
        return paperSubmissionService.findAll();
    }

    @RequestMapping(value = "/submissions", method = RequestMethod.POST)
    PaperSubmission save(@RequestBody PaperSubmission paperSubmission)
    {
        return paperSubmissionService.addSubmission(paperSubmission);
    }

    @RequestMapping(value = "/submissions/{id}", method = RequestMethod.GET)
    PaperSubmission getById(@PathVariable Long id)
    {
        return paperSubmissionService.findByID(id);
    }

    @RequestMapping(value = "submissions/{id}", method = RequestMethod.DELETE)
    void removeSubmission(@PathVariable Long id)
    {
        paperSubmissionService.removeSubmission(id);
    }

    @RequestMapping(value = "/submissions_by_id/{id}", method = RequestMethod.GET)
    List<PaperSubmission> findByUserID(@PathVariable Long id)
    {
        return paperSubmissionService.findByUserID(id);
    }
}

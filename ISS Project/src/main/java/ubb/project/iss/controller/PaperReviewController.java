package ubb.project.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ubb.project.iss.domain.PaperReview;
import ubb.project.iss.service.PaperReviewService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge=3600,allowedHeaders = "*", allowCredentials = "true")
public class PaperReviewController {
    @Autowired
    private PaperReviewService paperReviewService;

    @RequestMapping(value = "/paper_review", method = RequestMethod.GET)
    List<PaperReview> getPaperReviews() {
        return paperReviewService.getAll();
    }

    @RequestMapping(value = "/paper_review", method = RequestMethod.POST)
    PaperReview save(@RequestBody PaperReview paperReview) {
        return paperReviewService.save(paperReview);
    }

    @RequestMapping(value = "/paper_review/{id}", method = RequestMethod.GET)
    PaperReview getById(@PathVariable Long id) {
        return paperReviewService.getById(id);
    }

    @RequestMapping(value ="/review_result/", method = RequestMethod.POST)
    Boolean checkReview(){return paperReviewService.checkIfApproved();}

    @RequestMapping(value="/do_reviews/{id}", method = RequestMethod.POST)
    void doReviews(@PathVariable Long id){ paperReviewService.assignPapers(id); }
}

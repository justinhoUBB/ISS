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

    @RequestMapping(value ="/review_result/{id}", method = RequestMethod.POST)
    Boolean checkReview(Long id){return paperReviewService.checkIfApproved(id);}

    @RequestMapping(value = "/paper_review", method = RequestMethod.PUT)
    PaperReview update(@RequestBody PaperReview paperReview) {
        paperReviewService.update(paperReview);
        return paperReview;
    }


    @RequestMapping(value="/do_reviews/{id}", method = RequestMethod.GET)
    void doReviews(@PathVariable Long id){ paperReviewService.assignPapers(id); }
}

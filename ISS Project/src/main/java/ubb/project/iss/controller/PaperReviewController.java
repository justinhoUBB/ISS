package iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import iss.domain.PaperReview;
import iss.service.PaperReviewService;

import java.util.List;

@RestController
public class PaperReviewController {
    @Autowired
    private PaperReviewService paperReviewService;

    @RequestMapping(value = "/paper_review", method = RequestMethod.GET)
    List<PaperReview> getPaperReviews() {
        return paperReviewService.getAll();
    }

    @RequestMapping(value = "/paper_review", method = RequestMethod.POST)
    PaperReview save(@RequestBody PaperReview paperReview, long conferenceID) {
        return paperReviewService.save(paperReview);
    }

    @RequestMapping(value = "/paper_review/{id}", method = RequestMethod.GET)
    PaperReview getById(@PathVariable Long id) {
        return paperReviewService.getById(id);
    }
}

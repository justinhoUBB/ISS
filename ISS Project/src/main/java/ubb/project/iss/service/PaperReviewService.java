package ubb.project.iss.service;

import ubb.project.iss.domain.PaperReview;

import java.util.List;

public interface PaperReviewService {
    List<PaperReview> getAll();
    PaperReview save(PaperReview entity);
    PaperReview getById(Long id);
    void remove(PaperReview paperReview);
    void sendDenialEmail(String to);
    void sendApprovalEmail(String to);
    void assignPapers();
    void update(Long paperReviewID, Integer newRemark, String newRecommendation);
    boolean checkIfApproved();
}

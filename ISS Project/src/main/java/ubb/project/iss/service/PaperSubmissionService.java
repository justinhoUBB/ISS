package ubb.project.iss.service;

import ubb.project.iss.domain.PaperSubmission;

import java.util.ArrayList;

public interface PaperSubmissionService {
    PaperSubmission addSubmission(PaperSubmission paper_submission);
    PaperSubmission updateSubmission(PaperSubmission paper_submission);
    PaperSubmission findByID(Long id);
    void removeSubmission(Long submission_id);
    ArrayList<PaperSubmission> findAll();
    ArrayList<PaperSubmission> findByUserID(Long user_id);
    ArrayList<PaperSubmission> findByConferenceID(Long conference_id);
}

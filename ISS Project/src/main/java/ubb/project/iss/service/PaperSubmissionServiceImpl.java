package ubb.project.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ubb.project.iss.domain.PaperSubmission;
import ubb.project.iss.repository.PaperSubmissionRepository;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class PaperSubmissionServiceImpl implements PaperSubmissionService {
    @Autowired
    PaperSubmissionRepository paperSubmissionRepository;
    @Override
    public PaperSubmission addSubmission(PaperSubmission paper_submission) {
        return paperSubmissionRepository.save(paper_submission);
    }

    @Override
    public PaperSubmission updateSubmission(PaperSubmission paper_submission) {
        PaperSubmission oldPaperSubmission = paperSubmissionRepository.findById(paper_submission.getId()).get();
        paperSubmissionRepository.deleteById(oldPaperSubmission.getId());
        return paperSubmissionRepository.save(paper_submission);
    }

    @Override
    public PaperSubmission findByID(Long id) {
        return paperSubmissionRepository.findById(id).get();
    }

    @Override
    public void removeSubmission(Long submission_id) {
        paperSubmissionRepository.deleteById(submission_id);
    }

    @Override
    public ArrayList<PaperSubmission> findAll() {
        return (ArrayList<PaperSubmission>) paperSubmissionRepository.findAll();
    }

    @Override
    public ArrayList<PaperSubmission> findByUserID(Long user_id) {
        return (ArrayList<PaperSubmission>) paperSubmissionRepository.findAll().stream().
                filter(paperSubmission -> paperSubmission.getUser_id() == user_id).
                collect(Collectors.toList());
    }
}

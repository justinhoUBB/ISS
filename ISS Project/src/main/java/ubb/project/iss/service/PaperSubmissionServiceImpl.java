package ubb.project.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public PaperSubmission updateSubmission(PaperSubmission paper_submission) {
        PaperSubmission oldPaperSubmission = paperSubmissionRepository.findById(paper_submission.getId()).get();
        oldPaperSubmission.setPaper_id(paper_submission.getPaper_id());
        oldPaperSubmission.setUser_id(paper_submission.getUser_id());
        oldPaperSubmission.setConference_id(paper_submission.getConference_id());
        return oldPaperSubmission;
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

    @Override
    public ArrayList<PaperSubmission> findByConferenceID(Long conference_id) {
        return (ArrayList<PaperSubmission>) paperSubmissionRepository.findAll().stream().
                filter(paperSubmission -> paperSubmission.getConference_id() == conference_id).
                collect(Collectors.toList());
    }
}

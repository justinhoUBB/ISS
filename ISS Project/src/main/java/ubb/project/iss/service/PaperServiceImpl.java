package ubb.project.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ubb.project.iss.domain.Paper;
import ubb.project.iss.repository.PaperRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    private PaperRepository paperRepository;


    @Override
    public List<Paper> getAll() {
        List<Paper> result = paperRepository.findAll();
        return result;
    }

    @Override
    public Paper save(Paper entity) {
        return paperRepository.save(entity);
    }

    @Override
    public Paper paperReviewed(Long id){
        Paper oldPaper = paperRepository.findById(id).orElse(null);
        oldPaper.setReviewed(true);
        return oldPaper;
    }

    @Override
    @Transactional
    public Paper update(Paper paper) {
        Paper oldPaper = paperRepository.findById(paper.getId()).get();
        oldPaper.setContent(paper.getContent());
        oldPaper.setKeywords(paper.getKeywords());
        oldPaper.setList_of_authors(paper.getList_of_authors());
        oldPaper.setPublisher_id(paper.getPublisher_id());
        oldPaper.setTitle(paper.getTitle());
        return oldPaper;
    }

    @Override
    public void delete(Long id) {
        paperRepository.deleteById(id);
    }

    @Override
    public Paper getById(Long id) {
        Paper update = paperRepository.findById(id).orElse(new Paper());
        return update;
    }

    @Override
    public List<Paper> getPapersAtConference(Long id)
    {
        return paperRepository.findAll().stream().filter(paper -> paper.getConference_id() == id).collect(Collectors.toList());
    }
}
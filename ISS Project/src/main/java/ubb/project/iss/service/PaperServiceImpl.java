package ubb.project.iss.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ubb.project.iss.domain.Paper;
import ubb.project.iss.repository.PaperRepository;

import java.util.List;
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
    public Paper getById(Long id) {
        Paper update = paperRepository.findById(id).orElse(new Paper());
        return update;
    }
}

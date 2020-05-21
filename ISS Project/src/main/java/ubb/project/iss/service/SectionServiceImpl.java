package ubb.project.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ubb.project.iss.domain.Section;
import ubb.project.iss.repository.SectionRepository;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {
    @Autowired
    SectionRepository sectionRepository;

    @Override
    public List<Section> getAll() {
        return sectionRepository.findAll();
    }

    @Override
    public Section save(Section entity) {
        return sectionRepository.save(entity);
    }

    @Override
    public Section getById(Long id) {
        return sectionRepository.findById(id).get();
    }
}

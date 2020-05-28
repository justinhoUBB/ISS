package ubb.project.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ubb.project.iss.domain.Repartition;
import ubb.project.iss.domain.Section;
import ubb.project.iss.repository.RepartitionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepartitionServiceImpl implements RepartitionService {
    @Autowired
    RepartitionRepository repartitionRepository;
    @Autowired
    SectionService sectionService;
    @Override
    public Repartition addUserToSection(Repartition repartition) {
        return repartitionRepository.save(repartition);
    }

    @Override
    public List<Repartition> findByConferenceID(Long id) {
        return repartitionRepository.findAll().stream().filter(repartition -> {
            Section section = sectionService.getById(repartition.getSection_id());
            return section.getConference_id() == id;
        }).collect(Collectors.toList());
    }
}

package ubb.project.iss.service;

import ubb.project.iss.domain.Section;

import java.util.List;

public interface SectionService {
    List<Section> getAll();
    Section save(Section entity);
    Section getById(Long id);
}

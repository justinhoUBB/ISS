package ubb.project.iss.service;

import ubb.project.iss.domain.Repartition;

import java.util.List;

public interface RepartitionService {
    Repartition addUserToSection(Repartition repartition);
    List<Repartition> findByConferenceID(Long id);
}

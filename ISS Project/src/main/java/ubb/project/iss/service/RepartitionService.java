package iss.service;

import iss.domain.Repartition;

import java.util.List;

public interface RepartitionService {
    Repartition addUserToSection(Repartition repartition);
    List<Repartition> findByConferenceID(Long id);
}

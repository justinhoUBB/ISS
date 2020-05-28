package ubb.project.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ubb.project.iss.domain.Listener;
import ubb.project.iss.domain.Paper;
import ubb.project.iss.repository.ListenerRepository;

import java.util.List;

@Service
public class ListenerServiceImpl implements ListenerService {

    @Autowired
    private ListenerRepository listenerRepository;

    @Override
    public List<Listener> getAll() {
        List<Listener> result = listenerRepository.findAll();
        return result;
    }

    @Override
    public Listener save(Listener listener) {
        return listenerRepository.save(listener);
    }

    @Override
    public Listener getById(Long id) {
        Listener update = listenerRepository.findById(id).orElse(new Listener());
        return update;
    }
}
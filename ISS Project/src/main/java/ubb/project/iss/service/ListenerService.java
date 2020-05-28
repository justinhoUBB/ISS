package ubb.project.iss.service;

import ubb.project.iss.domain.Listener;
import ubb.project.iss.domain.PaperBid;

import java.util.List;

public interface ListenerService {
    List<Listener> getAll();
    Listener save(Listener listener);
    Listener getById(Long id);

}
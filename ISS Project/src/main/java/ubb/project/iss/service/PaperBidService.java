package ubb.project.iss.service;

import ubb.project.iss.domain.PaperBid;

import java.util.List;

public interface PaperBidService {
    List<PaperBid> getAll();
    PaperBid save(PaperBid entity);
    PaperBid getById(Long id);
}

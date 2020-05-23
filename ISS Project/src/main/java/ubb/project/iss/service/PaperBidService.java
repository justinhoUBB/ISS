package iss.service;

import iss.domain.PaperBid;

import java.util.List;

public interface PaperBidService {
    List<PaperBid> getAll();
    PaperBid save(PaperBid paperBid, Long conferenceID);
    PaperBid getById(Long id);
    void remove(PaperBid bid);
}

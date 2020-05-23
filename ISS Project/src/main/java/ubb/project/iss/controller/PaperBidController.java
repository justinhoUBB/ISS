package iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import iss.domain.PaperBid;
import iss.service.PaperBidService;

import java.util.List;

@RestController
public class PaperBidController {
    @Autowired
    private PaperBidService paperBidService;

    @RequestMapping(value = "/paper_bids", method = RequestMethod.GET)
    List<PaperBid> getPaperBids() {
        return paperBidService.getAll();
    }

    @RequestMapping(value = "/paper_bids", method = RequestMethod.POST)
    PaperBid save(@RequestBody PaperBid paperBid, long conferenceID) {
        return paperBidService.save(paperBid, conferenceID);
    }

    @RequestMapping(value = "/paper_bids/{id}", method = RequestMethod.GET)
    PaperBid getById(@PathVariable Long id) {
        return paperBidService.getById(id);
    }
}

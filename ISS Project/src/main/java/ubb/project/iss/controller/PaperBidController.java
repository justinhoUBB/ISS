package ubb.project.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ubb.project.iss.domain.PaperBid;
import ubb.project.iss.service.PaperBidService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge=3600,allowedHeaders = "*", allowCredentials = "true")
public class PaperBidController {
    @Autowired
    private PaperBidService paperBidService;

    @RequestMapping(value = "/paper_bids", method = RequestMethod.GET)
    List<PaperBid> getPaperBids() {
        return paperBidService.getAll();
    }

    @RequestMapping(value = "/paper_bids", method = RequestMethod.POST)
    void save(@RequestBody PaperBid paperBid) {
        paperBidService.save(paperBid);
    }

    @RequestMapping(value = "/paper_bids/{id}", method = RequestMethod.GET)
    PaperBid getById(@PathVariable Long id) {
        return paperBidService.getById(id);
    }
}

package iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import iss.domain.Paper;
import iss.service.PaperService;

import java.util.List;

@RestController
public class PaperController {
    @Autowired
    private PaperService paperService;

    @RequestMapping(value = "/papers", method = RequestMethod.GET)
    List<Paper> getPapers() {
        return paperService.getAll();
    }

    @RequestMapping(value = "/papers", method = RequestMethod.POST)
    Paper save(@RequestBody Paper paper) {
        return paperService.save(paper);
    }
    @RequestMapping(value = "/papers/{id}", method = RequestMethod.GET)
    Paper getById(@PathVariable Long id) {
        return paperService.getById(id);
    }
}
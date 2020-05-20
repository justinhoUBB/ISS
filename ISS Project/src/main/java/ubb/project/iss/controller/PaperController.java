package ubb.project.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ubb.project.iss.domain.Paper;
import ubb.project.iss.service.PaperServiceImpl;
import ubb.project.iss.service.ServiceInterface;

import java.util.List;
import java.util.Set;

@RestController
public class PaperController {
    @Autowired
    private ServiceInterface<Paper> paperService;

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

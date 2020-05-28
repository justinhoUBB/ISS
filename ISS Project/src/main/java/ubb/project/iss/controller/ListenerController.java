package ubb.project.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ubb.project.iss.domain.Listener;
import ubb.project.iss.service.ListenerService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge=3600,allowedHeaders = "*", allowCredentials = "true")
public class ListenerController {
    @Autowired
    private ListenerService listenerService;

    @RequestMapping(value = "/listeners", method = RequestMethod.GET)
    List<Listener> getAll() {
        return listenerService.getAll();
    }

    @RequestMapping(value = "/listeners", method = RequestMethod.POST)
    Listener save(@RequestBody Listener listener) {
        return listenerService.save(listener);
    }

    @RequestMapping(value = "/listeners/{id}", method = RequestMethod.GET)
    Listener getById(@PathVariable Long id) {
        return listenerService.getById(id);
    }
}

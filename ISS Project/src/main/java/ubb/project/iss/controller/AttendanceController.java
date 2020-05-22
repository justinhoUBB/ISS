package ubb.project.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ubb.project.iss.domain.Attendance;
import ubb.project.iss.service.AttendanceService;

import java.util.List;

@RestController
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @RequestMapping(value = "/attendences", method = RequestMethod.GET)
    List<Attendance> getConferences() {
        return attendanceService.getAll();
    }

    @RequestMapping(value = "/attendences", method = RequestMethod.POST)
    Attendance save(@RequestBody Attendance attendance) {
        return attendanceService.save(attendance);
    }

    @RequestMapping(value = "/attendencesx/{id}", method = RequestMethod.GET)
    Attendance getById(@PathVariable Long id) {
        return attendanceService.getById(id);
    }
}

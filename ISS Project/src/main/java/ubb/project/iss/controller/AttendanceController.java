package ubb.project.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ubb.project.iss.domain.Attendance;
import ubb.project.iss.domain.Conference;
import ubb.project.iss.domain.UserTable;
import ubb.project.iss.service.AttendanceService;

import java.util.List;

@RestController
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @RequestMapping(value = "/attendances", method = RequestMethod.GET)
    List<Attendance> getConferences()
    {
        return attendanceService.getAll();
    }

    @RequestMapping(value = "/attendances", method = RequestMethod.POST)
    Attendance save(@RequestBody Attendance attendance)
    {
        return attendanceService.save(attendance);
    }

    @RequestMapping(value = "/attendances/{id}", method = RequestMethod.GET)
    Attendance getById(@PathVariable Long id)
    {
        return attendanceService.getById(id);
    }

    @RequestMapping(value = "attendances/{id}", method = RequestMethod.DELETE)
    void cancelSignUp(@PathVariable Long id)
    {
        Attendance attendance = attendanceService.getById(id);
        attendanceService.removeAttendance(attendance);
    }

    @RequestMapping(value = "/users_attending/{id}", method = RequestMethod.GET)
    List<UserTable> getUsersAttendingConference(@PathVariable Long id)
    {
        return attendanceService.getAllUsersAttendingConference(id);
    }

    @RequestMapping(value = "/conferences_attended/{id}", method = RequestMethod.GET)
    List<Conference> getConferencesAttendedByUser(@PathVariable Long id)
    {
        return attendanceService.getAllConferencesAttendedByUser(id);
    }
}

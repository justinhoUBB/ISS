package ubb.project.iss.service;

import ubb.project.iss.domain.Attendance;

import java.util.List;

public interface AttendanceService {
    List<Attendance> getAll();
    Attendance save(Attendance entity);
    Attendance getById(Long id);
}

package ubb.project.iss.service;

import ubb.project.iss.domain.Attendance;
import ubb.project.iss.domain.Conference;
import ubb.project.iss.domain.UserTable;

import java.util.ArrayList;
import java.util.List;

public interface AttendanceService {
    List<Attendance> getAll();
    Attendance save(Attendance entity);
    Attendance getById(Long id);
    void removeAttendance(Attendance attendance);
    ArrayList<UserTable> getAllUsersAttendingConference(long conference_id);
    ArrayList<Conference> getAllConferencesAttendedByUser(long user_id);
}

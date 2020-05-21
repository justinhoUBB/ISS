package ubb.project.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ubb.project.iss.domain.Attendance;
import ubb.project.iss.domain.Conference;
import ubb.project.iss.domain.User;
import ubb.project.iss.repository.AttendanceRepository;
import ubb.project.iss.repository.ConferenceRepository;
import ubb.project.iss.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    AttendanceRepository attendanceRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ConferenceRepository conferenceRepository;

    @Override
    public List<Attendance> getAll() {
        return attendanceRepository.findAll();
    }

    @Override
    public Attendance save(Attendance userSignup) {
        return attendanceRepository.save(userSignup);
    }

    @Override
    public void removeAttendance(Attendance attendance) {
        attendanceRepository.delete(attendance);
    }

    @Override
    public Attendance getById(Long id) {
        return attendanceRepository.findById(id).get();
    }

    @Override
    public ArrayList<User> getAllUsersAttendingConference(long conference_id) {
        return (ArrayList<User>) attendanceRepository.findAll().stream().
                filter(attendance -> attendance.getConference_id() == conference_id).
                map(attendance -> userRepository.findById(attendance.getUser_id()).get()).
                collect(Collectors.toList());
    }

    @Override
    public ArrayList<Conference> getAllConferencesAttendedByUser(long user_id) {
        return (ArrayList<Conference>) attendanceRepository.findAll().stream().
                filter(attendance -> attendance.getUser_id() == user_id).
                map(attendance -> conferenceRepository.findById(attendance.getConference_id()).get()).
                collect(Collectors.toList());
    }
}
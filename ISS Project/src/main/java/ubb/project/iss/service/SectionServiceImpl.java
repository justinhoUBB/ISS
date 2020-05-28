package ubb.project.iss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ubb.project.iss.domain.*;
import ubb.project.iss.repository.ConferenceRepository;
import ubb.project.iss.repository.SectionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionServiceImpl implements SectionService {
    @Autowired
    SectionRepository sectionRepository;
    @Autowired
    PaperSubmissionService paperSubmissionService;
    @Autowired
    RepartitionService repartitionService;
    @Autowired
    ConferenceRepository conferenceRepository;
    @Autowired
    SupervisorService supervisorService;
    @Autowired
    AttendanceService attendanceService;

    @Override
    public List<Section> getAll() {
        return sectionRepository.findAll();
    }

    @Override
    public Section save(Section entity) {
        return sectionRepository.save(entity);
    }

    @Override
    public Section getById(Long id) {
        return sectionRepository.findById(id).get();
    }

    @Override
    public List<Section> getAllByConferenceID(Long id) {
        return sectionRepository.findAll().stream().filter(section -> section.getConference_id() == id).collect(Collectors.toList());
    }

    public void createSections(Long conference_id, String topics)
    {
        Conference conference = conferenceRepository.findById(conference_id).get();
        int numberOfRooms = conference.getNumber_of_rooms();
        ArrayList<Supervisor> supervisors = (ArrayList<Supervisor>) supervisorService.getByConferenceID(conference_id);
        for(int i = 0; i < numberOfRooms; i++)
        {
            sectionRepository.save(new Section(supervisors.get(i).getId(), conference_id,topics));//topics ii full string, am facut functie in front
        }
    }

    public void createRepartitions(Long conference_id)
    {
        Conference conference = conferenceRepository.findById(conference_id).get();
        int numberOfRooms = conference.getNumber_of_rooms();
        ArrayList<Repartition> repartitions = new ArrayList<>();
        ArrayList<Section> sections = (ArrayList<Section>) sectionRepository.findAll().stream().filter(section -> section.getConference_id() == conference_id).collect(Collectors.toList());
        ArrayList<PaperSubmission> submissions = paperSubmissionService.findByConferenceID(conference_id);
        int numberOfSubmissions = submissions.size();
        ArrayList<Supervisor> supervisors = (ArrayList<Supervisor>) supervisorService.getByConferenceID(conference_id);
        ArrayList<UserAccount> attendances = attendanceService.getAllUsersAttendingConference(conference_id);
        int sectionIndex = 0;
        for(int i = 0; i < numberOfSubmissions; i++)
        {
            repartitions.add(new Repartition(submissions.get(i).getUser_id(), sections.get(sectionIndex).getId()));
            sectionIndex++;
            if(sectionIndex == numberOfRooms)
            {
                sectionIndex = 0;
            }
        }
        for (int i = 0; i < numberOfRooms; i++)
        {
            repartitions.add(new Repartition(supervisors.get(i).getUser_id(), i));
        }
        // users will be evenly distributed
        sectionIndex = 0;
        for (UserAccount userAccount : attendances)
        {
            repartitions.add(new Repartition(userAccount.getId(), sectionIndex));
            sectionIndex++;
            if(sectionIndex == numberOfRooms)
            {
                sectionIndex = 0;
            }
        }
        for(Repartition repartition : repartitions)
        {
            repartitionService.addUserToSection(repartition);
        }
    }
}
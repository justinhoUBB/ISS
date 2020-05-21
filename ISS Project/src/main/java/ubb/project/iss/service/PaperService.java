package ubb.project.iss.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ubb.project.iss.domain.Paper;
import ubb.project.iss.repository.PaperRepository;

import java.util.List;

public interface PaperService {
    List<Paper> getAll();
    Paper save(Paper entity);
    Paper getById(Long id);
}

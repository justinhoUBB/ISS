package ubb.project.iss.service;

import org.springframework.web.multipart.MultipartFile;
import ubb.project.iss.domain.Paper;

import java.util.List;

public interface PaperService {


    List<Paper> getAll();
    Paper save(Paper paper);
    Paper update(Paper paper);
    void delete(Long id);
    Paper getById(Long id);

}

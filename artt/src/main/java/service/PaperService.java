package service;

import domain.Paper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PaperRepository;

@Service
public class PaperService extends BaseService<Long, Paper> {

    public static final Logger log = LoggerFactory.getLogger(PaperService.class);

    @Autowired
    public PaperService(PaperRepository repository) {
        super(repository);
    }

    public void addPaper(Paper paper) {
        log.trace("addPaper - method entered, paper={}", paper);
        repository.save(paper);
        log.trace("addPaper - exiting method");
    }

    @Override
    protected Logger getLogger() {
        return null;
    }
}

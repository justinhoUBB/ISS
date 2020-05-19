package service;

import domain.SteeringCommittee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.PaperRepository;


@Service
public class SteeringCommitteeService extends BaseService<Long, SteeringCommittee>{
    public static final Logger log=  LoggerFactory.getLogger(SteeringCommitteeService.class);

    @Autowired
    public SteeringCommitteeService(PaperRepository repository) {
        super(repository);
    }

    public void addSteeringCommittee(SteeringCommittee st){
        log.trace("addSteeringCommittee - method entered, st={}",st);
        repository.save(st);
        log.trace("addSteeringCommittee - existing method");
    }

    @Transactional(rollbackFor = Throwable.class)
    public void updateSteeringCommittee(Long id,String first_name,String last_name,String email){
        log.trace("updateSteeringCommittee - method entered, params: {}, {}, {}, {}",id,first_name,last_name,email);
        repository.findById(id)
                .ifPresent(st->{
                    st.setFirst_name(first_name);
                    st.setLast_name(last_name);
                    st.setEmail(email);
                    log.debug("updateSteeringCommittee - updated SteeringCommittee");
                });
        log.trace("updateSteeringCommittee - existing method");
    }

    @Override
    protected org.slf4j.Logger getLogger() {
        return null;
    }
}

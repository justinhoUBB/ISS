package ubb.project.iss.service;

import org.springframework.stereotype.Service;
import ubb.project.iss.domain.AbstractTable;

import java.util.List;

@Service
public class AbstractServiceImpl implements AbstractService {
    @Override
    public List<AbstractTable> getAll() {
        return null;
    }

    @Override
    public AbstractTable save(AbstractTable entity) {
        return null;
    }

    @Override
    public AbstractTable getById(Long id) {
        return null;
    }
}

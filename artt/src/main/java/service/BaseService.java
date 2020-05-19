package service;

import domain.BaseEntity;
import org.slf4j.Logger;
import org.springframework.data.domain.Sort;
import repository.PaperRepository;
import repository.Repository;
import service.exceptions.ConferenceRuntimeException;

import java.io.Serializable;
import java.util.List;

public abstract class BaseService<ID extends Serializable, T extends BaseEntity<ID>> implements IService
{
    protected abstract Logger getLogger();

    protected Repository<T, ID> repository;

    BaseService(PaperRepository repository)
    {
        this.repository = (Repository<T, ID>) repository;
    }

    public void setRepository(Repository<T, ID> repo)
    {
        this.repository = repo;
    }

    public List<T> getAll()
    {
        getLogger().trace("getAll - entering method");
        List<T> all = repository.findAll();
        getLogger().debug("getAll - fetched items={}", all);
        return all;
    }

    public List<T> getAllSortedAscendingByFields(List<String> fields)
    {
        getLogger().trace("getAllSortedAscendingByFields - entering method, field={}", fields);
        List<T> all = repository.findAll(Sort.by(Sort.Direction.ASC, fields.toArray(new String[0])));
        getLogger().debug("getAllSortedAscendingByFields - fetched items={}", all);
        return all;
    }

    public List<T> getAllSortedDescendingByFields(List<String> fields)
    {
        getLogger().trace("getAllSortedDescendingByFields - entering method, fields={}", fields);
        List<T> all = repository.findAll(Sort.by(Sort.Direction.DESC, fields.toArray(new String[0])));
        getLogger().debug("getAllSortedDescendingByFields - fetched items={}", all);
        return all;
    }

    public T getById(ID id)
    {
        getLogger().trace("getById - entering method, id={}", id);
        T item = repository.findById(id).orElseThrow(() -> new ConferenceRuntimeException("Invalid id."));
        getLogger().debug("getById - fetched item={}", item);
        return item;
    }
}
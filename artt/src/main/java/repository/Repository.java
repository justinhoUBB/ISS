package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface Repository<T, ID extends Serializable> extends JpaRepository<T, ID> {
}

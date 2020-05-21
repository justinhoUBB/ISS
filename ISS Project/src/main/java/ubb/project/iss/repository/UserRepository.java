package ubb.project.iss.repository;

import ubb.project.iss.domain.User;

// nu ne trebuie repo-uri separate pt fiecare tip de user. o sa-i diferentiem prin alte metode
public interface UserRepository extends Repository<User, Long> {
}

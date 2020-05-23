package ubb.project.iss.repository;

import ubb.project.iss.domain.UserTable;

// nu ne trebuie repo-uri separate pt fiecare tip de user. o sa-i diferentiem prin alte metode
public interface UserRepository extends Repository<UserTable, Long> {
}

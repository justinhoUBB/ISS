package iss.repository;

import iss.domain.UserAccount;

// nu ne trebuie repo-uri separate pt fiecare tip de user. o sa-i diferentiem prin alte metode
public interface UserRepository extends Repository<UserAccount, Long> {
}

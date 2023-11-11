package com.project.blognow.repository;
import com.project.blognow.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Account,Long> {
    Optional<Account> findOneByEmailIgnoreCase(String email);
}

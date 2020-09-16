package com.yellow.pepper.context.infrastructure.persistence.repository;

import com.yellow.pepper.context.infrastructure.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Account entity repository interface
 */
@Repository
public interface IAccountEntityRepository extends JpaRepository<AccountEntity, String> {
  Optional<AccountEntity> findByNumber(String number);
}

package com.company.LibraryProject.repository;

import com.company.LibraryProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserIdAndDeletedAtIsNull(Integer userId);

    Optional<User> findByUsernameAndDeletedAtIsNullAndEnabledIsTrue(String username);


    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);


}

package com.diego.exxhangesecurity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

import com.diego.exxhangesecurity.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    @Query("SELECT DISTINCT user FROM User user " +
            "INNER JOIN FETCH user.authorities AS authorities " +
            "WHERE user.username = :username")
    Optional<User> findByUsername(@Param("username")String usename);
    Page<User> findByUsernameContains(String usename, Pageable pageable);
    Page<User> findAllByUsername(String usename, Pageable pageable);
    Page<User> findAllByUsernameContainsAndUsername(String usenames, String auth, Pageable pageable);

    Boolean existsByUsername(String usename);
}
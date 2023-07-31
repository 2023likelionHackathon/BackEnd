package com.project.market.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    @Query(" select u from User u " +
            "left join fetch u.boardList b " +
            "where u.id = :userId ")
    User findUserWithBoards(@Param("userId")Long userId);
}

package com.project.market.repository;

import com.project.market.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    @Query(" select u from User u " +
            "left join fetch u.boardList b " +
            "where u.id = :userId ")
    Optional<User> findUserWithBoards(@Param("userId")Long userId);

    Optional<User> findByUserId(String userId);

    Optional<User> findByNickname(String nickname);
}

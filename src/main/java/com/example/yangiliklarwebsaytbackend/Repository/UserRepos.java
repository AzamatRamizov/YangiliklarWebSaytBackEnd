package com.example.yangiliklarwebsaytbackend.Repository;

import com.example.yangiliklarwebsaytbackend.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepos extends JpaRepository<Users,Integer> {
    boolean existsByUsername(String username);

    Optional<Users> findByUsername(String username);

    Optional<Users> findByUsernameAndCode(String username, String code);

}

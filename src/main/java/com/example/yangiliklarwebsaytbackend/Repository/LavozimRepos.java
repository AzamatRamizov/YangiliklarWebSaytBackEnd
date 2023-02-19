package com.example.yangiliklarwebsaytbackend.Repository;

import com.example.yangiliklarwebsaytbackend.Entity.Lavozim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LavozimRepos extends JpaRepository<Lavozim,Integer> {
    Optional<Lavozim> findByLavozimNomi(String lavozimNomi);

}

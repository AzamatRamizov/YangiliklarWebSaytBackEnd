package com.example.yangiliklarwebsaytbackend.Repository;

import com.example.yangiliklarwebsaytbackend.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepos extends JpaRepository<Post,Integer> {

}

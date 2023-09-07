package com.sparta.myblogbackend.repository;

import com.sparta.myblogbackend.entity.Post;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


public interface PostRepository extends JpaRepository<Post, Long> {



}

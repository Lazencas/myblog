package com.sparta.myblogbackend.repository;

import com.sparta.myblogbackend.entity.Comment;
import com.sparta.myblogbackend.entity.Post;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findByIdOrderByCreatedAtDesc(Long Id);
}

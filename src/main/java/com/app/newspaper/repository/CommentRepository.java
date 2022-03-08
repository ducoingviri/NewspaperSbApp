package com.app.newspaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.newspaper.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {



}

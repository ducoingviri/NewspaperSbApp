package com.app.newspaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.newspaper.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {



}

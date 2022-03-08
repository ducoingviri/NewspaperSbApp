package com.app.newspaper.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.app.newspaper.entity.Comment;
import com.app.newspaper.repository.CommentRepository;
import com.app.newspaper.entity.Post;
import com.app.newspaper.repository.PostRepository;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentRepository repository;

    @GetMapping("")
    public ResponseEntity<List<Comment>> index() {
        try {
            List<Comment> items = new ArrayList<Comment>();
            repository.findAll().forEach(items::add);
            if (items.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> show(@PathVariable("id") Long id) {
        try {
            Optional<Comment> item = repository.findById(id);
            if (item.isPresent()) {
                return new ResponseEntity<>(item.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    PostRepository postRepository;

    @PostMapping("/{post_id}")
    public ResponseEntity<Comment> store(@PathVariable("post_id") Long post_id, @RequestBody Comment item) {
        try {
            Optional<Post> post = postRepository.findById(post_id);
            if (!post.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Comment obj = new Comment();
            obj = item;
            obj.setPost(post.get());

            Comment _item = repository.save(obj);
            return new ResponseEntity<>(_item, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> update(@PathVariable("id") Long id, @RequestBody Comment item) {
        try {
            Optional<Comment> data = repository.findById(id);
            if (data.isPresent()) {
                Comment _item = data.get();
		if (item.getEmail() != null) _item.setEmail(item.getEmail());
		if (item.getContent() != null) _item.setContent(item.getContent());
		if (item.getCreatedat() != null) _item.setCreatedat(item.getCreatedat());
		Optional<Post> post = postRepository.findById(item.getPost().getId());
		if (item.getPost() != null) _item.setPost(post.get());
                return new ResponseEntity<>(repository.save(_item), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> destroy(@PathVariable("id") Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
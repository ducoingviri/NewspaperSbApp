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
import com.app.newspaper.entity.Post;
import com.app.newspaper.repository.PostRepository;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostRepository repository;

    @GetMapping("")
    public ResponseEntity<List<Post>> index() {
        try {
            List<Post> items = new ArrayList<Post>();
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
    public ResponseEntity<Post> show(@PathVariable("id") Long id) {
        try {
            Optional<Post> item = repository.findById(id);
            if (item.isPresent()) {
                return new ResponseEntity<>(item.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<Post> store(@RequestBody Post item) {
        try {
            Post obj = new Post();
            obj = item;
            Post _item = repository.save(obj);
            return new ResponseEntity<>(_item, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> update(@PathVariable("id") Long id, @RequestBody Post item) {
        try {
            Optional<Post> data = repository.findById(id);
            if (data.isPresent()) {
                Post _item = data.get();
		if (item.getAuthor() != null) _item.setAuthor(item.getAuthor());
		if (item.getTitle() != null) _item.setTitle(item.getTitle());
		if (item.getBrief() != null) _item.setBrief(item.getBrief());
		if (item.getContent() != null) _item.setContent(item.getContent());
		if (item.getCreatedat() != null) _item.setCreatedat(item.getCreatedat());
		if (item.getIspublished() != null) _item.setIspublished(item.getIspublished());
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
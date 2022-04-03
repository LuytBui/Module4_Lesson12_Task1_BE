package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/v1/blogs")
public class BlogRestController {
    @Autowired
    IBlogService blogService;


    @GetMapping
    public ResponseEntity<Iterable<Blog>> findAll(@RequestParam(name = "q") Optional<String> q,
                                                  @RequestParam(name = "pageSize") Optional<Integer> pageSize) {
        if (q.isPresent())
            return new ResponseEntity<>(blogService.search(q.get()), HttpStatus.OK);
        if (pageSize.isPresent())
            return new ResponseEntity<>(blogService.findAll(pageSize.get()), HttpStatus.OK);
        return new ResponseEntity<>(blogService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> findOne(@PathVariable Long id) {
        Optional<Blog> blog = blogService.findById(id);
        if (!blog.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(blog.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Blog> save(@RequestBody Blog blog) {
        return new ResponseEntity<>(blogService.save(blog), HttpStatus.OK);
    }


}

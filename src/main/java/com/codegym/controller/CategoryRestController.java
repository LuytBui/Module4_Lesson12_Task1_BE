package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.IBlogService;
import com.codegym.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/v1/categories")
public class CategoryRestController {
    @Autowired
    ICategoryService categoryService;

    @Autowired
    IBlogService blogService;

    @GetMapping
    public ResponseEntity<Iterable<Category>> findAll(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Iterable<Blog>> findOne(@PathVariable Long id){
        Optional<Category> category = categoryService.findById(id);
        if (!category.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Iterable<Blog> blogs = blogService.findAllByCategory(category.get());
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

}

package com.codegym.service;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBlogService extends IGeneralService<Blog> {

    Iterable<Blog> findAll(int pageSize);

    Iterable<Blog> findAllByCategory(Category category);

    Iterable<Blog> search(String q);
}

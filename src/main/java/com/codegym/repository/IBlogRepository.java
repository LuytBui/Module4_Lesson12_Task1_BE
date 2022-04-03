package com.codegym.repository;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBlogRepository extends PagingAndSortingRepository<Blog, Long> {

    Iterable<Blog> findAllByCategory(Category category);

    @Query(value = "select * from blog limit ?1", nativeQuery = true)
    Iterable<Blog> findAll(int pageSize);

    Iterable<Blog> findAllByTittleContainingOrContentContaining(String tittle, String name);
}

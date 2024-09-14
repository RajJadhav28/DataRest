package com.example.starter.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.starter.models.Blog;

public interface BlogRepo extends CrudRepository<Blog, Integer>{

}

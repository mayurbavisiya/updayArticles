package com.upday.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.upday.domain.Article;

@RepositoryRestResource
public interface ArticlesRepository extends CrudRepository<Article, Integer>{
}

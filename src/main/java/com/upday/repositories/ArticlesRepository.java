package com.upday.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.upday.domain.Article;

@RepositoryRestResource
public interface ArticlesRepository extends CrudRepository<Article, Integer>{

	
	@Query("SELECT a FROM Article a WHERE upper(a.authorName)=upper(:names)")     // 2. Spring JPA In cause using @Query
    List<Article> findByAuthorName(@Param("names") String authorName);
	
//	@Query("SELECT a FROM Article a WHERE a. publishedDate between '2019-09-29' and '2019-09-29'")    
	@Query("SELECT a FROM Article a WHERE a. publishedDate between :fromDate and :toDate ")
    List<Article> findByDates(@Param("fromDate") Date fromDate , @Param("toDate") Date toDate);
	
	@Query("SELECT a FROM Article a WHERE upper(keyWords) LIKE CONCAT('%',upper(:keyword),'%')")
    List<Article> findByKeywords(@Param("keyword") String keyWord);
}

package com.upday.services;

import java.util.Date;

import com.upday.domain.Article;
	
public interface ArticleService {
	Iterable<Article> listAllArticles();

	Article getArticleById(Integer id);

	Iterable<Article> getArticleByAuthor(String id);

	Iterable<Article> getArticleByDates(Date fromDate, Date toDate);

	Iterable<Article> getArticleByKeywords(String keyword);
	
	Article saveArticle(Article article);

	Article updateArticle(Article storedArticle, Article newArticle);

	void deleteArticle(Integer id);
}



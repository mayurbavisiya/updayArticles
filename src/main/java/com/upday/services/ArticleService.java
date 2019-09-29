package com.upday.services;


import com.upday.domain.Article;

public interface ArticleService {
    Iterable<Article> listAllArticles();

    Article getArticleById(Integer id);

    Article saveArticle(Article article);
    
    Article updateArticle(Article storedArticle, Article newArticle);

    void deleteArticle(Integer id);
}

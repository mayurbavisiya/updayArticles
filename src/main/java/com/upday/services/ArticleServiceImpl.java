package com.upday.services;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upday.domain.Article;
import com.upday.repositories.ArticlesRepository;

@Service
public class ArticleServiceImpl implements ArticleService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticlesRepository articleRepository;

	@Autowired
	public void setArticleRepository(ArticlesRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	@Override
	public Iterable<Article> listAllArticles() {
		logger.debug("listAllArticles called");
		return articleRepository.findAll();
	}

	@Override
	public Article getArticleById(Integer id) {
		logger.debug("getArticleById called");
		return articleRepository.findById(id).orElse(null);
	}

	@Override
	public Article saveArticle(Article article) {
		logger.debug("saveArticle called");
		article.setPublishedDate(new Date());
		return articleRepository.save(article);
	}
	
	@Override
	public Article updateArticle(Article storedArticle , Article newArticle) {
		logger.debug("updateArticle called");
		storedArticle.setUpdatedDate(new Date());
		storedArticle.setArticleHeader(newArticle.getArticleHeader());
		storedArticle.setArticleShortDesc(newArticle.getArticleShortDesc());
		storedArticle.setArticleText(newArticle.getArticleText());
		storedArticle.setKeyWords(newArticle.getKeyWords());
		storedArticle.setAuthorName(newArticle.getAuthorName());
		return articleRepository.save(storedArticle);
	}
	

	@Override
	public void deleteArticle(Integer id) {
		logger.debug("deleteArticle called");
		articleRepository.deleteById(id);
	}
}

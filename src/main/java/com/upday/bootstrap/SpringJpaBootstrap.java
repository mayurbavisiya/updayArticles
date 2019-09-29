package com.upday.bootstrap;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.upday.domain.Article;
import com.upday.repositories.ArticlesRepository;

@Component
public class SpringJpaBootstrap implements
		ApplicationListener<ContextRefreshedEvent> {

	private ArticlesRepository articleRepository;

	private Logger log = LogManager.getLogger(SpringJpaBootstrap.class);

	@Autowired
	public void setArticleRepository(ArticlesRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		loadArticles();
	}

	private void loadArticles() {
		Article article = new Article();
		article.setPublishedDate(new Date());
		article.setArticleHeader("Spring Boot Rest API");
		article.setArticleShortDesc("In this article we will discus about rest api.");
		article.setArticleText("A RESTful API is an application program interface (API) that uses HTTP requests to GET, PUT, POST and DELETE data. ... The REST used by browsers can be thought of as the language of the internet. With cloud use on the rise, APIs are emerging to expose web services.");
		article.setAuthorName("Mayur");
		article.setKeyWords("Spring Boot,Rest,API,Java");
		articleRepository.save(article);

		log.info("Saved Article - id: " + article.getId());

	}

}

package com.upday.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.upday.configuration.RepositoryConfiguration;
import com.upday.domain.Article;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { RepositoryConfiguration.class })
public class ArticlesRepositoryTest {

	private Logger logger = LogManager.getLogger(ArticlesRepositoryTest.class);

	private ArticlesRepository articleRepository;

	@Autowired
	public void setArticlesRepository(ArticlesRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	@Test
	public void testSaveArticle() {
		// setup Article
		Article article = new Article();
		article.setPublishedDate(new Date());
		article.setArticleHeader("Spring Boot Rest API");
		article.setArticleShortDesc("In this article we will discus about rest api.");
		article.setArticleText("A RESTful API is an application program interface (API) that uses HTTP requests to GET, PUT, POST and DELETE data. ... The REST used by browsers can be thought of as the language of the internet. With cloud use on the rise, APIs are emerging to expose web services.");
		article.setAuthorName("Mayur");
		article.setKeyWords("Spring Boot,Rest,API,Java");
//		article.setId(10);
		// save Article, verify has ID value after save
		assertNull(article.getId()); // null before save
		articleRepository.save(article);
		assertNotNull(article.getId()); // not null after save

		// fetch from DB
		Article fetchedArticle = articleRepository.findById(article.getId())
				.orElse(null);
		// should not be null
		assertNotNull(fetchedArticle);
		// should equal
		assertEquals(article.getId(), fetchedArticle.getId());
		assertEquals(article.getArticleShortDesc(),
				fetchedArticle.getArticleShortDesc());
		// update description and save
		fetchedArticle.setArticleShortDesc("New Description");
		articleRepository.save(fetchedArticle);
		// get from DB, should be updated
		Article fetchedUpdatedArticle = articleRepository.findById(
				fetchedArticle.getId()).orElse(null);
		assertEquals(fetchedArticle.getArticleShortDesc(),
				fetchedUpdatedArticle.getArticleShortDesc());
		// verify count of Articles in DB
		long ArticleCount = articleRepository.count();
		assertEquals(ArticleCount, 1);
		// get all Articles, list should only have one
		Iterable<Article> Articles = articleRepository.findAll();
		int count = 0;
		for (Article a : Articles) {
			logger.debug(a.getId());
			count++;
		}
		assertEquals(count, 1);
	}
}

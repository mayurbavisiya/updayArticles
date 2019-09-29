package com.upday.bootstrap;

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
		// Article mug = new Article();
		// mug.setDescription("Spring Framework Guru Mug");
		// mug.setImageUrl("https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_coffee_mug-r11e7694903c348e1a667dfd2f1474d95_x7j54_8byvr_512.jpg");
		// mug.setProductId("168639393495335947");
		// mug.setPrice(new BigDecimal("11.95"));
		// articleRepository.save(mug);

		
//		article.setPublishedDate(new Date());
//		article.setAuthorName("Mayur");
//		article.setArticleShortDesc("This is my First Article.This article is about Sprging boot API.");
//		articleRepository.save(article);

//		log.info("Saved Shirt - id: " + article.getId());

	}

}

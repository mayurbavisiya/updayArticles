package com.upday.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.upday.domain.Article;
import com.upday.repositories.ArticlesRepository;

public class ArticleServiceImplMockTest {

	private ArticleServiceImpl articleServiceImpl;
	@Mock
	private ArticlesRepository articleRepository;
	@Mock
	private Article article;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		articleServiceImpl = new ArticleServiceImpl();
		articleServiceImpl.setArticleRepository(articleRepository);
	}

	@Test
	public void shouldReturnArticle_whenGetArticleByIdIsCalled()
			throws Exception {
		when(articleRepository.findById(5)).thenReturn(Optional.of(article));
		Article retrievedArticle = articleServiceImpl.getArticleById(5);
		assertThat(retrievedArticle, is(equalTo(article)));

	}

	@Test
	public void shouldReturnArticle_whenSaveArticleIsCalled() throws Exception {
		when(articleRepository.save(article)).thenReturn(article);
		Article savedArticle = articleServiceImpl.saveArticle(article);
		assertThat(savedArticle, is(equalTo(article)));
	}

}
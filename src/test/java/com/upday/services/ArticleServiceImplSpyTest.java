package com.upday.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.never;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.upday.domain.Article;
import com.upday.repositories.ArticlesRepository;

@RunWith(MockitoJUnitRunner.class)
public class ArticleServiceImplSpyTest {
	@Spy
	private ArticleServiceImpl prodServiceSpy;
	@Mock
	private ArticlesRepository articlesRepository;
	@Mock
	private Article Article;

	@Test(expected = NullPointerException.class)
	public void shouldThrowNullPointerException_whenGetArticleByIdIsCalledWithoutContext()
			throws Exception {
		Article retrievedArticle = prodServiceSpy.getArticleById(5);
		assertThat(retrievedArticle, is(equalTo(Article)));
	}

	public void shouldThrowNullPointerException_whenSaveArticleIsCalledWithoutContext()
			throws Exception {
		Mockito.doReturn(Article).when(articlesRepository).save(Article);
		Article savedArticle = prodServiceSpy.saveArticle(Article);
		assertThat(savedArticle, is(equalTo(Article)));
	}

	@Test
	public void shouldVerifyThatGetArticleByIdIsCalled() throws Exception {
		Mockito.doReturn(Article).when(prodServiceSpy).getArticleById(5);
		Article retrievedArticle = prodServiceSpy.getArticleById(5);
		Mockito.verify(prodServiceSpy).getArticleById(5);
	}

	@Test
	public void shouldVerifyThatSaveArticleIsNotCalled() throws Exception {
		Mockito.doReturn(Article).when(prodServiceSpy).getArticleById(5);
		Article retrievedArticle = prodServiceSpy.getArticleById(5);
		Mockito.verify(prodServiceSpy, never()).saveArticle(Article);
	}
}
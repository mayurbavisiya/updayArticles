package com.upday.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.upday.domain.Article;
import com.upday.exceptionhandler.RecordNotFoundException;
import com.upday.services.ArticleService;

@RestController
@RequestMapping("/articles")
@Api(value = "articles", description = "Operations pertaining to articles on Upday for Samsung App")
public class ArticlesController {

	private ArticleService articleService;

	@Autowired
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	@ApiOperation(value = "View a list of available articles", response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public Iterable<Article> list(Model model) {
		Iterable<Article> articleList = articleService.listAllArticles();
		return articleList;
	}

	@ApiOperation(value = "Add an Article")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> saveArticle(@RequestBody Article article) {
		articleService.saveArticle(article);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Custom-Header", "Article saved successfully");
		return new ResponseEntity<>(null, headers, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "Update an article")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity updateArticle(@PathVariable Integer id,
			@RequestBody Article article) {
		Article storedArticle = articleService.getArticleById(id);
		if(storedArticle != null){
			articleService.updateArticle(storedArticle, article);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Custom-Header", "Article updated successfully");
			return new ResponseEntity(null, headers, HttpStatus.OK);
		}else{
			throw new RecordNotFoundException("Record not found");
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "Delete an Article")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity delete(@PathVariable Integer id) {
		articleService.deleteArticle(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Custom-Header", "Article deleted successfully");
		return new ResponseEntity(null, headers, HttpStatus.OK);

	}

	@ApiOperation(value = "Search an article with an Author Name", response = Article.class)
	@RequestMapping(value = "/showArticleByAuthor/{authorName}", method = RequestMethod.GET, produces = "application/json")
	public Iterable<Article> getArticleByAuthor(
			@PathVariable String authorName, Model model) {
		return articleService.getArticleByAuthor(authorName);
	}

	@ApiOperation(value = "Search an article by dates", response = Article.class)
	@RequestMapping(value = "/showArticleByDate/{fromDate}/{toDate}", method = RequestMethod.GET, produces = "application/json")
	public Iterable<Article> getArticlesByDates(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDate,
			Model model) throws Exception {		
			return articleService.getArticleByDates(fromDate, toDate);			
		
	}

	@ApiOperation(value = "Search an article by keywords", response = Article.class)
	@RequestMapping(value = "/showArticleBykeyWord/{keyWord}", method = RequestMethod.GET, produces = "application/json")
	public Iterable<Article> getArticlesBykeyWords(
			@PathVariable String keyWord, Model model) {
		return articleService.getArticleByKeywords(keyWord);
	}
	
}

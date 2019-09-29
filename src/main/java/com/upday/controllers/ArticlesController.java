package com.upday.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.upday.domain.Article;
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

	@ApiOperation(value = "Search a article with an ID", response = Article.class)
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET, produces = "application/json")
	public Article showArticle(@PathVariable Integer id, Model model) {
		Article article = articleService.getArticleById(id);
		return article;
	}

	@ApiOperation(value = "Add a Article")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity saveArticle(@RequestBody Article article) {
		articleService.saveArticle(article);
		return new ResponseEntity(HttpStatus.OK);
	}

	@ApiOperation(value = "Update a article")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity updateArticle(@PathVariable Integer id,
			@RequestBody Article article) {
		Article storedArticle = articleService.getArticleById(id);
		articleService.updateArticle(storedArticle , article);
		return new ResponseEntity("Article updated successfully", HttpStatus.OK);
	}

	@ApiOperation(value = "Delete a Article")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity delete(@PathVariable Integer id) {
		articleService.deleteArticle(id);
		return new ResponseEntity("Article deleted successfully", HttpStatus.OK);

	}

}



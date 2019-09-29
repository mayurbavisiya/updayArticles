package com.upday.domain;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "Article")
@Table(name = "article")
public class Article {
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(hidden = true)
	@Column(name = "ARTICLE_ID")
	private Integer id;

	@Column(name = "ARTICLEHEADER")
	@ApiModelProperty(notes = "The articles in details", required = true)
	private String articleHeader;

	@Column(name = "ARTICLESHORTDESC")
	@ApiModelProperty(notes = "The articles in details", required = true)
	private String articleShortDesc;

	@Column(name = "ARTICLETEXT")
	@Lob
	@ApiModelProperty(notes = "The articles in details", required = true)
	private String articleText;

	@Column(name = "PUBLISHEDDATE")
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "Abu Dhabi/Muscut")
	@ApiModelProperty(hidden = true)
	private Date publishedDate;

	@Column(name = "UPDATEDDATE")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "Abu Dhabi/Muscut")
	@ApiModelProperty(hidden = true)
	private Date updatedDate;

	@Column(name = "AUTHORNAME")
	@ApiModelProperty(notes = "The name of the author of the article", required = true)
	private String authorName;

	@Column(name = "KEYWORDS")
	@ApiModelProperty(notes = "keywords of the article", required = true)
	private String keyWords;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArticleHeader() {
		return articleHeader;
	}

	public void setArticleHeader(String articleHeader) {
		this.articleHeader = articleHeader;
	}

	public String getArticleShortDesc() {
		return articleShortDesc;
	}

	public void setArticleShortDesc(String articleShortDesc) {
		this.articleShortDesc = articleShortDesc;
	}

	public String getArticleText() {
		return articleText;
	}

	public void setArticleText(String articleText) {
		this.articleText = articleText;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}

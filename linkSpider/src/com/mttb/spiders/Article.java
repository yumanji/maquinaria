package com.mttb.spiders;

import java.util.Date;

public class Article {

	private Integer id;
	private String id_original;
	private Integer id_category;
	private String article_url;
	private String content;
	private Integer count = 1;
	private Integer active = 1;
	private Date created;
	private Date last_review;
	private Date deleted;
	
	
	/**
	 * @param article_url
	 */
	public Article(String article_url) {
		super();
		this.article_url = article_url;
		this.id_category = 0;
		this.id_original = "";
		this.content = "";
		this.active = 1;
		this.count = 1;
		this.created = new Date();
		this.last_review = new Date();
		
		String[] parts = article_url.split("/");
		this.id_original = parts[parts.length - 2];	// Cojo la pen�ltima parte de la URL como ID, dado que parece ser ese el ID �nico
	}
	
	public Article() {
		super();
		this.active = 1;
		this.count = 1;
		this.created = new Date();
		this.last_review = new Date();
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getId_original() {
		return id_original;
	}
	public void setId_original(String id_original) {
		this.id_original = id_original;
	}
	public Integer getId_category() {
		return id_category;
	}
	public void setId_category(Integer id_category) {
		this.id_category = id_category;
	}
	public String getArticle_url() {
		return article_url;
	}
	public void setArticle_url(String article_url) {
		this.article_url = article_url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getLast_review() {
		return last_review;
	}
	public void setLast_review(Date last_review) {
		this.last_review = last_review;
	}
	public Date getDeleted() {
		return deleted;
	}
	public void setDeleted(Date deleted) {
		this.deleted = deleted;
	}

	
	
	
	
	public void grabar() {
		MySQLAccess dao = new MySQLAccess();
		
		try {
			dao.createArticle(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		dao.close();
		
	}
	
	public void actualizar() {
		MySQLAccess dao = new MySQLAccess();
		
		try {
			dao.updateArticle(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		dao.close();
		
	}
	
	public void recargar() {
		MySQLAccess dao = new MySQLAccess();
		
		try {
			dao.reloadArticle(this);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		dao.close();
		
	}
	
	
	
}

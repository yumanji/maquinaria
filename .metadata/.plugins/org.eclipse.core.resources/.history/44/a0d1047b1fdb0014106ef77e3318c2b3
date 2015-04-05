package com.mttb.spiders;

import java.util.Date;

public class Categoria {
	private Integer id;
	private String id_original;
	private Integer id_parent;
	private Integer category_level;
	private String category_url;
	private Integer es_final = 0;
	private Date created;
	private Date last_review;
	private Date deleted;
	
	
	/**
	 * @param id
	 * @param id_original
	 * @param id_parent
	 * @param category_level
	 * @param category_url
	 * @param created
	 * @param last_review
	 * @param deleted
	 */
	public Categoria(Integer id, String id_original, Integer id_parent,
			Integer category_level, String category_url, Integer es_final, Date created,
			Date last_review, Date deleted) {
		super();
		this.id = id;
		this.id_original = id_original;
		this.id_parent = id_parent;
		this.category_level = category_level;
		this.category_url = category_url;
		this.es_final = es_final;
		this.created = created;
		this.last_review = last_review;
		this.deleted = deleted;
	}
	
	public Integer getEs_final() {
		return es_final;
	}

	public void setEs_final(Integer es_final) {
		this.es_final = es_final;
	}

	public Categoria(Integer id_parent,
			Integer category_level, String category_url) {
		super();
		this.id_parent = id_parent;
		this.id_original = "0";
		this.es_final = 0;
		this.category_level = category_level;
		this.category_url = category_url;
		this.created = new Date();
	}
	
	public Categoria(String category_url) {
		super();

		this.category_url = category_url;
		this.id_original = "0";
		this.id_parent = 0;
		this.es_final = 0;
		this.category_level = 1;
		this.created = new Date();
	}

	public Categoria(Integer id) {
		super();
		this.id = id;
		this.id_original = "0";
		this.category_url = "";
		this.id_parent = 0;
		this.es_final = 0;
		this.category_level = 1;
		this.created = new Date();
	}
	public Categoria() {
		super();
		this.id_original = "0";
		this.category_url = "";
		this.id_parent = 0;
		this.es_final = 0;
		this.category_level = 1;
		this.created = new Date();
	}

	
	public String getId_original() {
		return id_original;
	}

	public void setId_original(String id_original) {
		this.id_original = id_original;
	}

	public Integer getId_parent() {
		return id_parent;
	}

	public void setId_parent(Integer id_parent) {
		this.id_parent = id_parent;
	}

	public Integer getCategory_level() {
		return category_level;
	}

	public void setCategory_level(Integer category_level) {
		this.category_level = category_level;
	}

	public String getCategory_url() {
		return category_url;
	}

	public void setCategory_url(String category_url) {
		this.category_url = category_url;
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

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	
	public void grabar() {
		MySQLAccess dao = new MySQLAccess();
		
		try {
			dao.createCategory(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		dao.close();
		
	}
	
	public void actualizar() {
		MySQLAccess dao = new MySQLAccess();
		
		try {
			dao.updateCategory(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		dao.close();
		
	}
	
	public void recargar() {
		MySQLAccess dao = new MySQLAccess();
		
		try {
			dao.reloadCategory(this);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		dao.close();
		
	}
	
	}

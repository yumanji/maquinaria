package com.mttb.spiders;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Article {

	private int id;
	private String id_original;
	private int id_category;
	private String article_url;
	private String content;
	private int count = 1;
	private int active = 1;
	private Date created;
	private Date last_review;
	private Date deleted;

	public static WebDriver driver = new FirefoxDriver();
	
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
	
	public void actualizarContent() {
		MySQLAccess dao = new MySQLAccess();
		
		try {
			dao.registerContent(this);
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
	
	
	public static List<Article> getArticles(int quantity) {
		MySQLAccess dao = new MySQLAccess();
		List<Article> listaArticulos = null;
		try {
			listaArticulos = dao.getArticles(quantity);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		dao.close();
		return listaArticulos;
	}
	public static List<Article> getArticles() {
		MySQLAccess dao = new MySQLAccess();
		List<Article> listaArticulos = null;
		try {
			listaArticulos = dao.getArticles(10);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		dao.close();
		return listaArticulos;
	}
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//driver.navigate().to("http://www.agriaffaires.es/");
		int maxDepth = 50, cont = 1;
		List<Article> listaArticulos = null;
		String url = "http://www.agriaffaires.es/usado/1/maquinaria-agricola.html";
		
		listaArticulos = getArticles(maxDepth);
		Iterator<Article> ListaArt= listaArticulos.iterator();
		//datosCategoria.put("level", "1");
		//datosCategoria.put("id", "1");
		//datosCategoria.put("parent", "1");
		while (ListaArt.hasNext()) {
			Article articulo = ListaArt.next();
			System.out.println(Integer.toString(cont).concat(" Articulo ").concat(articulo.getId_original()).concat(" con url ").concat(articulo.getArticle_url()));
			String contenido = getArticleContent(articulo);
			//System.out.println("Contenido ".concat(contenido));
			articulo.setContent(contenido);
			articulo.actualizarContent();
			cont++;
		}
		//String url = "http://www.agriaffaires.es";
		int contadorPaginas=0;
		
	}


	public static String getArticleContent (Article articulo) {
		// Recupera la lista de enlaces de las categor�as o subcategor�as
		String content = "";
		String url = articulo.getArticle_url();
		List<String> completo =  new ArrayList<String>();
		
		if(url.isEmpty() || url.length() == 0) return null;
		//System.out.println("Viajando a ".concat(url));
		driver.navigate().to(url);
		List contenidos = driver.findElements(By.cssSelector(".annonceDetail"));	// tabla de la ficha del articulo
		//System.out.println("Encontrados ".concat(Integer.toString(contenidos.size())).concat(" elementos"));
		Iterator<WebElement> it = contenidos.iterator();
		while (it.hasNext())
		{
			
			WebElement elemento = (WebElement) it.next();
			completo.add(elemento.getAttribute("innerHTML"));
			//System.out.println("Contenido ".concat(content));
			//System.out.println("Contenido2 ".concat(elemento.getAttribute("innerHTML")));
		}
		content.concat("</table>");
		//content = contenido.getAttribute("innerHTML");
		//contenido.getText()
		String joinedResult = StringUtils.join(completo, "</table><table>");
		
		return "<table>".concat(joinedResult).concat("</table");
		//return content;
	}
	
}

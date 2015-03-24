package testra;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new FirefoxDriver();
		driver.navigate().to("https://sedeapl.dgt.gob.es//WEB_TTRA_CONSULTA/Todos.faces?idioma=es");
		int contadorPaginas=0;
		Set listaEdictos = new HashSet(); 
		Set listaPdfs = new HashSet(); 
		while (contadorPaginas<1)
		{
			List all_links_webpage = driver.findElements(By.tagName("a"));		
			WebElement paginaSiguiente = driver.findElement(By.id("paginacion:siguiente"));
			Iterator<WebElement> it = all_links_webpage.iterator();
			while (it.hasNext())
			{
				WebElement elemento = (WebElement) it.next();
				String urlEnlace = elemento.getAttribute("href");
				if (urlEnlace.contains("VisualizacionEdicto") && (urlEnlace.contains("subidioma%3Des")))
				{
					listaEdictos.add(elemento.getAttribute("href"));
					//elemento.click();
					
/*					WebDriverWait wait = new WebDriverWait(driver, 3);
					wait.until(ExpectedConditions.elementToBeClickable(By.tagName("img"))); 
					System.out.println(driver.getPageSource());
					List all_images_webpage = driver.findElements(By.tagName("img"));		
					Iterator<WebElement> itImg = all_links_webpage.iterator();
					while (itImg.hasNext())
					{
						WebElement pdf = (WebElement) it.next();
						System.out.println(pdf.getAttribute("src"));
/*						if (pdf.getAttribute("src").contains("=PDF"))
						{
							System.out.println("Pdf encontrado:" + pdf.getAttribute("src"));													
						}
*/
				}
			}		
			paginaSiguiente.click();
			contadorPaginas++;
		}
		
		Iterator itEdictos = listaEdictos.iterator();
		while (itEdictos.hasNext())
		{
			String urlEdicto = (String) itEdictos.next();
			System.out.println(urlEdicto);					
			driver.navigate().to(urlEdicto);
			List all_images_webpage = driver.findElements(By.tagName("a"));		
			Iterator<WebElement> itImg = all_images_webpage.iterator();
			while (itImg.hasNext())
			{
				WebElement pdf = (WebElement) itImg.next();
				if (pdf.getAttribute("href").contains("=PDF"))
				{
					System.out.println(pdf.getAttribute("href"));													
					listaPdfs.add(pdf.getAttribute("href"));
				}
				
			}
		}
		driver.close();	
	}

}

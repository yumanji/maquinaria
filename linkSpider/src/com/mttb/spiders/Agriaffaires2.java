package com.mttb.spiders;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Agriaffaires2 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://www.agriaffaires.es/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testAgriaffaires2() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.cssSelector("#listeRub > ul.pac_RubMere > li > h4 > a")).click();
    driver.findElement(By.linkText("Tractor agricola")).click();
    driver.findElement(By.linkText("John Deere 6930")).click();
    driver.findElement(By.cssSelector("img.linkbuttunNavigation")).click();
    driver.findElement(By.cssSelector("img[alt=\"Case IH MAGNUM 335\"]")).click();
    driver.findElement(By.linkText("Volver a la lista")).click();
    driver.findElement(By.xpath("//div[@id='tri-group']/button")).click();
    driver.findElement(By.xpath("//div[@id='tri-group']/button")).click();
    driver.findElement(By.xpath("//div[@id='tri-group']/button")).click();
    driver.findElement(By.id("divLienPlus")).click();
    driver.findElement(By.linkText("Sub-encabezamientos")).click();
    driver.findElement(By.linkText("Tractor frutero")).click();
    driver.findElement(By.xpath("(//a[contains(text(),'Aperos no accionados para el trabajo del suelo')])[2]")).click();
    driver.findElement(By.linkText("Sub-encabezamientos")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

package com.mttb.spiders;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class AgriaffairesCompleto {
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
  public void testAgriaffairesCompleto() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.id("902")).click();
    driver.findElement(By.cssSelector("#listeRub > ul.pac_RubMere > li > h4 > a")).click();
    driver.findElement(By.linkText("Tractores viñedos")).click();
    driver.findElement(By.cssSelector("#flechePrix > b")).click();
    driver.findElement(By.id("chkAdNotPrice")).click();
    driver.findElement(By.linkText("página siguiente")).click();
    driver.findElement(By.linkText("página siguiente")).click();
    driver.findElement(By.linkText("Home")).click();
    driver.findElement(By.id("904")).click();
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

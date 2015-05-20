package scripts;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import testUtils.Helper;

public class SetDefaultLaunch extends Helper{
  @Test
  public void setToDefaultAndLaunch() throws Exception {
	  Reporter.log("shiva"+"<br>");
	  Reporter.log("Krishna"+"<br>");
	  h.setCaps();
	  driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
	  driver.findElement(By.className("UIAPopover")).findElement(By.name("Settings")).click();
	  WebElement settingMenu = driver.findElement(By.className("UIAPopover")).findElement(By.className("UIATableCell"));
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  HashMap<String, String> scrollObject = new HashMap<String, String>();
	  scrollObject.put("direction", "down");
	  scrollObject.put("element", (((RemoteWebElement) settingMenu).getId()));
	  js.executeScript("mobile: scroll", scrollObject);
	  if (driver.findElement(By.className("UIAPopover")).findElements(By.name("RESET TO DEFAULTS")).isEmpty()) {
		  Assert.assertTrue(false, "Reset to Defaults is missing");
	  }
	  driver.findElement(By.className("UIAPopover")).findElement(By.name("RESET TO DEFAULTS")).click();
//	  driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
//	  driver.findElement(By.className("UIAPopover")).findElement(By.name("Settings")).click();
//	  driver.findElement(By.className("UIAPopover")).findElement(By.name("Light Theme")).click();
	  
	  driver.quit();
	  h.setCaps();
	
	  if (driver.findElement(By.className("UIATableView")).findElements(By.className("UIATableCell")).isEmpty()) {
		  Assert.assertTrue(false, "Reset to default not applied correctly");
	  }
  }
}

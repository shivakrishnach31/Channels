package testUtils;

import io.appium.java_client.ios.IOSDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;

import scripts.TestBase;

public class Helper extends TestBase {
	
	
	public void setCaps() throws MalformedURLException {
		// TODO Auto-generated method stub
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "iPadAir");
		capabilities.setCapability("browserName","");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("platformVersion", "8.3");
		capabilities.setCapability("rotatable", true);
		//File appDir= new File("/Users/shiva/Desktop");
		//File app = new File(appDir,"SimpleLoginApplication.app");
		//capabilities.setCapability("app", "http://sezdsk24772:8080/job/ios-Shure-ChannelsApp-CI/lastSuccessfulBuild/artifact/build/Release-iphoneos/ShureMobile.ipa");
		capabilities.setCapability("app","com.shure.plus.channels");
		capabilities.setCapability("udid", "7c7a3d3626200bb38205013f479adf90fdf585f5");
		//capabilities.setCapability("bundleID", "com.shure.plus.channels");
		//capabilities.setCapability("fullReset", "true");
		//capabilities.setCapability("browserName", "safari");
		driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		
	}
	public void scrollDownChannels(){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "down");
		scrollObject.put("element", ((RemoteWebElement) driver.findElement(By.className("UIATableCell"))).getId());
		js.executeScript("mobile: scroll", scrollObject);
	}
	public void channelsList(){
		channelsList = driver.findElement(By.className("UIATableView")).findElements(By.className("UIATableCell"));
	}
	
	public void demoChannelsList(){
		demoChannelsList=driver.findElementByClassName("UIATableView").findElements(By.className("UIATableCell"));
	}
	
	public void resetToDefaults(){
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
	}
	
	public void isSorted(ArrayList<String> s){
		boolean isSorted = true;
		for(int i = 0; i < s.size() - 1; i++) {
		   // current String is > than the next one (if there are equal list is still sorted)
		   if(s.get(i).compareToIgnoreCase(s.get(i + 1)) > 0) { 
		       isSorted = false;
		       break;
		   }
		   
		}System.out.println("This List is Sorted : "+isSorted);
	}
	
	
	
}

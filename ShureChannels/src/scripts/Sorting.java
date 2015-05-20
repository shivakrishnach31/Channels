package scripts;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import testUtils.Helper;

public class Sorting extends Helper{
	@Test
	public void channelsSort() throws InterruptedException {
		ArrayList<String> sortedChannelNames = new ArrayList<String>();
		ArrayList<String> sortedDeviceNames = new ArrayList<String>();
		ArrayList<String> sortedDeviceModels = new ArrayList<String>();
		ArrayList<String> sortedFrequencies = new ArrayList<String>();
		driver.findElementByXPath(" //UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]").click();
		List<WebElement> sortOptions = driver.findElementByClassName("UIAPopover").findElement(By.className("UIAScrollView")).findElements(By.className("UIAStaticText"));
		
		for (int j = 0; j < sortOptions.size(); j++) {
			System.out.println(sortOptions.get(j).getText());
			if (j>0) {
				driver.findElementByXPath(" //UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]").click();
				sortOptions = driver.findElementByClassName("UIAPopover").findElement(By.className("UIAScrollView")).findElements(By.className("UIAStaticText"));
//				JavascriptExecutor js = (JavascriptExecutor) driver;
//				HashMap<String, String> scrollObject = new HashMap<String, String>();
//				scrollObject.put("direction", "down");
//				scrollObject.put("element", ((RemoteWebElement) driver.findElementByClassName("UIAPopover").findElement(By.className("UIAScrollView")).findElement(By.className("UIAStaticText"))).getId());
//				js.executeScript("mobile: scroll", scrollObject);
			}
			
			
			sortOptions.get(j).click();
//			sortOptions.get(j).click();
			
			if (sortOptions.get(j).getText().equalsIgnoreCase("Channel Name")) {
				
				//*********************************************************************************************************
				
				if (driver.findElement(By.className("UIAPopover")).findElement(By.name("DONE")).isEnabled()==false) {
					driver.findElement(By.className("UIAPopover")).findElement(By.name("CANCEL")).click();
				} else {
					driver.findElement(By.className("UIAPopover")).findElement(By.name("DONE")).click();
				}
				h.channelsList();
				
				for (int i = 0; i < channelsList.size(); i++) {
					if (!channelsList.get(i).getAttribute("name").startsWith("[")) {
						sortedChannelNames.add(i,channelsList.get(i).getAttribute("name"));
					} else {
						sortedChannelNames.add(i,channelsList.get(i).findElements(By.className("UIAStaticText")).get(2).getText());
					}
				}
				
				
				
				System.out.println(sortedChannelNames);
				h.isSorted(sortedChannelNames);
			}
			
			//*********************************************************************************************************
			
			if (sortOptions.get(j).getText().equalsIgnoreCase("Device Name")) {
				if (driver.findElement(By.className("UIAPopover")).findElement(By.name("DONE")).isEnabled()==false) {
					driver.findElement(By.className("UIAPopover")).findElement(By.name("CANCEL")).click();
				} else {
					driver.findElement(By.className("UIAPopover")).findElement(By.name("DONE")).click();
				}
				h.channelsList();
				
				for (int i = 0; i < channelsList.size(); i++) {
					if (channelsList.get(i).findElements(By.className("UIAStaticText")).get(2).getText().startsWith("[")) {
						sortedDeviceNames.add(i,channelsList.get(i).findElements(By.className("UIAStaticText")).get(2).getText());
					} else {
						sortedDeviceNames.add(i,channelsList.get(i).getAttribute("name"));
					}
				}
				
				System.out.println(sortedDeviceNames);
				h.isSorted(sortedDeviceNames);
			}
			
			//*********************************************************************************************************
			
			if (sortOptions.get(j).getText().equalsIgnoreCase("Device Model")) {
				if (driver.findElement(By.className("UIAPopover")).findElement(By.name("DONE")).isEnabled()==false) {
					driver.findElement(By.className("UIAPopover")).findElement(By.name("CANCEL")).click();
				} else {
					driver.findElement(By.className("UIAPopover")).findElement(By.name("DONE")).click();
				}
				h.channelsList();
				
				for (int i = 0; i < channelsList.size(); i++) {
					
					
					if (channelsList.get(i).findElements(By.className("UIAStaticText")).get(3).getText().endsWith("MHz")) {
						sortedDeviceModels.add(i,channelsList.get(i).findElements(By.className("UIAStaticText")).get(1).getText());
					} else {
						sortedDeviceModels.add(i,channelsList.get(i).findElements(By.className("UIAStaticText")).get(3).getText());
					}
					
				//	sortedDeviceModels.add(i,channelsList.get(i).findElements(By.className("UIAStaticText")).get(3).getText());
					
				}
				System.out.println(sortedDeviceModels);
				h.isSorted(sortedDeviceModels);
			}
			
			//*********************************************************************************************************
			
			if (sortOptions.get(j).getText().equalsIgnoreCase("Frequency")) {
				if (driver.findElement(By.className("UIAPopover")).findElement(By.name("DONE")).isEnabled()==false) {
					driver.findElement(By.className("UIAPopover")).findElement(By.name("CANCEL")).click();
				} else {
					driver.findElement(By.className("UIAPopover")).findElement(By.name("DONE")).click();
				}
				h.channelsList();
				
				for (int i = 0; i < channelsList.size(); i++) {
					if (channelsList.get(i).findElements(By.className("UIAStaticText")).get(3).getText().endsWith("MHz")) {
						sortedFrequencies.add(i,channelsList.get(i).findElements(By.className("UIAStaticText")).get(3).getText());
					} else {
						sortedFrequencies.add(i,channelsList.get(i).findElements(By.className("UIAStaticText")).get(1).getText());
					}
					//sortedFrequencies.add(i,channelsList.get(i).findElements(By.className("UIAStaticText")).get(1).getText());
					
				}
				System.out.println(sortedFrequencies);
				h.isSorted(sortedFrequencies);
			}
		}
		
		
		
		
	}
}

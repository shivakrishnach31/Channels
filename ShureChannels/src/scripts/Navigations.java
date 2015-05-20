package scripts;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Rotatable;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.testng.Assert;
import org.testng.annotations.Test;

import testUtils.Helper;

public class Navigations extends Helper{
	
	
	@Test(enabled=false) // Navigations and Getting Selected Channel details & Info
	public void ChannelNavigations() throws Exception {
		
		List<WebElement> detailsOfChannel; // List for storing Selected Channel details from Controlling Panel

		//******** Selecting and Clicking a Channel randomly and getting the Channel Name********//
		//***************************************************************************************//
		final int selectedChannel = random.nextInt(channelsList.size());
		System.out.println("Selected Channel Index"+" is "+selectedChannel);
		channelsList.get(selectedChannel).click();
		Thread.sleep(2000);
		String channelName = channelsList.get(selectedChannel).getAttribute("name");
		System.out.println("Selected Channel Name is "+channelName);
		if (channelName.startsWith("[")) {
			channelName = channelsList.get(selectedChannel).findElements(By.className("UIAStaticText")).get(2).getText();
		}
		System.out.println("Selected Channel Name is "+channelName);

		//******** Rotating Screen Orientation to Landscape *********//
		//***********************************************************//
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		((Rotatable)augmentedDriver).rotate(ScreenOrientation.LANDSCAPE);


		//******** Getting Channels info based on the Device ID:: Have to get details from tableview 4 when selected device is Anxient *********//
		//**************************************************************************************************************************************//
		if (driver.findElementsByClassName("UIASegmentedControl").isEmpty()) {
			detailsOfChannel = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[3]").findElements(By.className("UIATableCell"));
			System.out.println("Selected Channel is non Anxient Device."+" And have below "+detailsOfChannel.size()+" details");
		} else {
			detailsOfChannel = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[4]").findElements(By.className("UIATableCell"));
			System.out.println("Selected Channel is Anxient Device."+" And have below "+detailsOfChannel.size()+" details");
		}

		//******** Storing selected channel details into a Hash Map and printing them *********//
		//*************************************************************************************//
		HashMap<String, String> channelDetails = new HashMap<String, String>();
		for (int i = 0; i < detailsOfChannel.size(); i++) {
			List<WebElement> details = detailsOfChannel.get(i).findElements(By.className("UIAStaticText"));
			//Thread.sleep(4000);
			channelDetails.put(details.get(0).getText(), details.get(1).getText());

			System.out.println(details.get(0).getText()+" is "+details.get(1).getText());
		}

		//******** Checking whether the selected channel showing correct values in Controlling panel *********//
		//****************************************************************************************************//
		System.out.println("Channel Name from Controlling Panel is "+channelDetails.get("Channel Name").toString());
		Assert.assertEquals(channelName, channelDetails.get("Channel Name").toString());
		System.out.println("Selected Channel details matched with the details in the Controlling Panel");

		//******** Getting information of the Selected Channel, storing into Hash Map and Printing that info *********//
		//************************************************************************************************************//
		driver.findElementByName("smc icon info").click();
		List<WebElement> channelInfo = driver.findElementByClassName("UIAPopover").findElements(By.className("UIATableCell"));
		System.out.println(channelInfo.size());
		HashMap<String, String> detailedChannelInfo = new HashMap<String, String>();
		System.out.println("***** Selected Channel Information*****");
		for (int i = 0; i < channelInfo.size(); i++) {
			detailedChannelInfo.put((channelInfo.get(i).findElements(By.className("UIAStaticText")).get(0).getText()),(channelInfo.get(i).findElements(By.className("UIAStaticText")).get(1)).getText());
			System.out.println((channelInfo.get(i).findElements(By.className("UIAStaticText")).get(0).getText())+" is "+(channelInfo.get(i).findElements(By.className("UIAStaticText")).get(1)).getText());
		}
		//System.out.println(detailedChannelInfo);
		driver.findElementByName("smc icon info").click();
		System.out.println(driver.findElements(By.name("Tap to exit DEMO mode")).isEmpty());
		((Rotatable)augmentedDriver).rotate(ScreenOrientation.PORTRAIT);
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------//
	@Test //Navigations and Getting Selected Channel details & Info
	
	public void demoModeNavigations(){
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
		driver.findElement(By.className("UIAPopover")).findElement(By.name("Demo Mode")).click();
		h.demoChannelsList();
		System.out.println("*******Demo Channels are:*******");
		for (WebElement demoChannel : demoChannelsList) {
			System.out.println(demoChannel.getAttribute("name"));
		}
		int selectedDemoChannel = random.nextInt(demoChannelsList.size());
		demoChannelsList.get(selectedDemoChannel).click();
		System.out.println("Selected Demo Channel is"+" "+demoChannelsList.get(selectedDemoChannel).getAttribute("name"));
		
		
		List<WebElement> demoChannelDetails;
		List<WebElement> RxAndTxButtons; 
		if (driver.findElementsByClassName("UIASegmentedControl").isEmpty()) {
			demoChannelDetails = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[3]").findElements(By.className("UIATableCell"));
			System.out.println("****Selected Demo Channel is non Anxient Device."+" And have below "+demoChannelDetails.size()+" details****");
		} else {
			demoChannelDetails = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[4]").findElements(By.className("UIATableCell"));
			System.out.println("****Selected Demo Channel is an Anxient Device."+" And have below "+demoChannelDetails.size()+" details****");
		}
		
		HashMap<String, String> detailsDemoChannel = new HashMap<String, String>();
		for (int i = 0; i < demoChannelDetails.size(); i++) {
			List<WebElement> demoDetails = demoChannelDetails.get(i).findElements(By.className("UIAStaticText"));
			//Thread.sleep(4000);
			detailsDemoChannel.put(demoDetails.get(0).getText(), demoDetails.get(1).getText());

			System.out.println(demoDetails.get(0).getText()+" is "+demoDetails.get(1).getText());
		}
		
		
		System.out.println("Channel Name from Controlling Panel is "+detailsDemoChannel.get("Channel Name").toString());
		Assert.assertEquals(demoChannelsList.get(selectedDemoChannel).getAttribute("name"), detailsDemoChannel.get("Channel Name").toString());
		System.out.println("Selected Channel details matched with the details in the Controlling Panel");
	
		
		driver.findElementByName("smc icon info").click();
		List<WebElement> selectedDemoChannelInfo = driver.findElementByClassName("UIAPopover").findElements(By.className("UIATableCell"));
		System.out.println(selectedDemoChannelInfo.size());
		HashMap<String, String> detailedDemoChannelInfo = new HashMap<String, String>();
		System.out.println("***** Selected Channel Information*****");
		for (int i = 0; i < selectedDemoChannelInfo.size(); i++) {
			detailedDemoChannelInfo.put((selectedDemoChannelInfo.get(i).findElements(By.className("UIAStaticText")).get(0).getText()),(selectedDemoChannelInfo.get(i).findElements(By.className("UIAStaticText")).get(1)).getText());
			System.out.println((selectedDemoChannelInfo.get(i).findElements(By.className("UIAStaticText")).get(0).getText())+" is "+(selectedDemoChannelInfo.get(i).findElements(By.className("UIAStaticText")).get(1)).getText());
		}
		//System.out.println(detailedChannelInfo);
		driver.findElementByName("smc icon info").click();
		
		
		if (!driver.findElementsByClassName("UIASegmentedControl").isEmpty()) {
			RxAndTxButtons = driver.findElementByClassName("UIASegmentedControl").findElements(By.className("UIAButton"));
			for (WebElement webElement : RxAndTxButtons) {
				if (webElement.getText().contains("Linked")) {
					webElement.click();
					break;
				}
			}
			//List<WebElement> txDeviceGroups = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[4]").findElements(By.className("UIATableGroup"));
			List<WebElement> txDevices = driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[4]").findElements(By.className("UIATableCell"));
			HashMap<String, String> txDeviceDetails = new HashMap<String, String>();		
			
			for (int i = 0; i < RxAndTxButtons.size(); i++) {
				
				if (RxAndTxButtons.get(i).getText().contains("2")) {
					for (int j = 0; j < 10; j++) {
						txDeviceDetails.put(txDevices.get(j).findElements(By.className("UIAStaticText")).get(0).getAttribute("name"),txDevices.get(j).findElements(By.className("UIAStaticText")).get(1).getAttribute("name"));
						System.out.println(txDevices.get(j).findElements(By.className("UIAStaticText")).get(0).getAttribute("name")+" is "+txDevices.get(j).findElements(By.className("UIAStaticText")).get(1).getAttribute("name"));
					}
					
				} else if (RxAndTxButtons.get(i).getText().contains("1")) {
					for (int j = 0; j < 5; j++) {
						txDeviceDetails.put(txDevices.get(j).findElements(By.className("UIAStaticText")).get(0).getAttribute("name"),txDevices.get(j).findElements(By.className("UIAStaticText")).get(1).getAttribute("name"));
						System.out.println(txDevices.get(j).findElements(By.className("UIAStaticText")).get(0).getAttribute("name")+" is "+txDevices.get(j).findElements(By.className("UIAStaticText")).get(1).getAttribute("name"));
					}
				}
			}
			//System.out.println(txDeviceDetails);
				
			
			
		}
		
		
	}
}

package scripts;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import scripts.TestBase;

public class Filters extends TestBase{

	@Test
	public void filterTests() throws Exception {
		
		//h.setCaps();
		
		Thread.sleep(15000);
		//h.channelsList();
		
		//********Scrolling down Channels************//
		//*******************************************//
		
		h.scrollDownChannels();
		//List<WebElement> channelsList = driver.findElement(By.className("UIATableView")).findElements(By.className("UIATableCell"));
		
		//******** Getting Online and Offline defines*******///
		//**************************************************//
		
		System.out.println(channelsList.size());
		List<String> offlineDevices = new ArrayList<String>();
		List<String> onlineDevices = new ArrayList<String>();
		System.out.println("**** All the Channels ****");
		try {
			for (int i = 0; i<channelsList.size(); i++) {
				if (channelsList.get(i).findElement(By.className("UIAStaticText")).getText().startsWith("[")) {
					System.out.println("Channel "+(i+1)+" is "+channelsList.get(i).findElements(By.className("UIAStaticText")).get(2).getText());
					if (channelsList.get(i).isEnabled() == false) {

						offlineDevices.add(channelsList.get(i).findElements(By.className("UIAStaticText")).get(2).getText());
					}
					else{

						onlineDevices.add(channelsList.get(i).findElements(By.className("UIAStaticText")).get(2).getText());
					}
				} else {
					System.out.println("Channel "+(i+1)+" is "+channelsList.get(i).findElement(By.className("UIAStaticText")).getText());
					if (channelsList.get(i).isEnabled() == false) {

						offlineDevices.add(channelsList.get(i).findElement(By.className("UIAStaticText")).getText());
					}
					else{

						onlineDevices.add(channelsList.get(i).findElement(By.className("UIAStaticText")).getText());
					}
				}

			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		
			
			System.out.println("**** Offline Devices are ****");
			for (int i = 0; i < offlineDevices.size(); i++) {
				
				System.out.println(offlineDevices.get(i));
			}
			System.out.println("**** Online Devices are ****");
			for (int i = 0; i < onlineDevices.size(); i++) {
				
				System.out.println(onlineDevices.get(i));
			}
		
		
		//******** Number of Filters*******//
		//*********************************//
		
		List<WebElement> filters = driver.findElementByClassName("UIAToolbar").findElements(By.className("UIAButton"));
		System.out.println("Total number of filters are"+" "+filters.size()); 
		
		//******** Clicking each filter and getting the channels from that filter*******//
		//******************************************************************************//
		
		List<WebElement> offlineDevicesInOfflineTab = new ArrayList<WebElement>();
		List<WebElement> lowBatteryDevices = new ArrayList<WebElement>();
		List<WebElement> interferenceDevices = new ArrayList<WebElement>();
		
		for (int i = 1; i < filters.size();i++) {
			
			filters.get(i).click();
		
			//******** Getting information of Offline Devices************	
			if (i==1) {
				System.out.println("Moving to Offline Devices Filter tab");
				offlineDevicesInOfflineTab = driver.findElementByClassName("UIATableView").findElements(By.className("UIATableCell"));
				if (offlineDevicesInOfflineTab.size()==0) {
					System.err.println("There are no Offline Devices");
				} else {
					for (int j = 0; j < offlineDevicesInOfflineTab.size(); j++) {
						//System.out.println(offlineDevicesInOfflineTab.get(j).getAttribute("name"));
						List<WebElement> channelInfo = offlineDevicesInOfflineTab.get(j).findElements(By.className("UIAStaticText"));
						for (int k = 0; k < channelInfo.size(); k++) {
							System.out.println(channelInfo.get(k).getText());
						}
						if (!offlineDevicesInOfflineTab.get(j).isEnabled()==false) {
							System.err.println("This Channel is not grayed out in Offline Devices Tab");
						}else {
							System.out.println("This channel is grayed out as Expected in Offline Devices tab");
						}
					}
				}
			}
			//******* Getting Information of Low Battery Devices********************
			if (i==2) {
				System.out.println("Moving to Low Battery Devices Filter tab");
				lowBatteryDevices = driver.findElementByClassName("UIATableView").findElements(By.className("UIATableCell"));
				if (lowBatteryDevices.size()==0) {
					System.err.println("There are no low Battery Devices");
				} else {
					for (int j = 0; j < lowBatteryDevices.size(); j++) {
						System.out.println(lowBatteryDevices.get(j).getAttribute("name"));
					}
				}
			}
			//******* Getting Information of Interferences********************
			if (i==3) {
				System.out.println("Moving to Interference Devices Filter tab");
				interferenceDevices = driver.findElementByClassName("UIATableView").findElements(By.className("UIATableCell"));
				
				if (interferenceDevices.size()==0) {
					System.err.println("There are no interferences");
				} else {
					for (int j = 0; j < interferenceDevices.size(); j++) {
						System.out.println(interferenceDevices.get(j).getAttribute("name"));
					}
				}
			}
			
			
		}
		
		

		
		
		
			     	
			     	
	}
}
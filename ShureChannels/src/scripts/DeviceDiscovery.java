package scripts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import testUtils.Helper;

public class DeviceDiscovery extends Helper{
	
	/****************************************************************************
	***** Verifying Devices Discovery by Starting,Stopping and restarting the App
	*****************************************************************************/
	@Test(priority=0)
	public void offlineCheck() throws Exception {
		driver.quit();
		h.beforeSuite();
		//driver.launchApp();
		System.out.println("App is launched and searching for Devices...");
		Thread.sleep(5000);
		int initialLaunch = channelsList.size();
		if (initialLaunch==0) {
			System.out.println("There are no Devices in the Network");
		}
		System.out.println("Closing the session and Relaunching the App");
		//driver.closeApp();
		//driver.launchApp();
		driver.quit();
		h.beforeSuite();
		h.channelsList();
		System.out.println("App is relaunched");
		Thread.sleep(5000);
		int afterRelaunch = channelsList.size();
		Assert.assertEquals(initialLaunch, afterRelaunch);
		System.out.println("Devices found correctly after starting,stopping and relaunching the App");
		System.out.println("Closing the Session");
		System.out.println("******************************************************");
//		driver.closeApp();
//		driver.launchApp();
		driver.quit();
		
	}
	
	
	/***************************************************************************************
	 ***** Verifying Devices by Launching the App, Closing and Re launching the App.
	 ***************************************************************************************/
	@Test(priority=1)
	public void fewOnlineCheck() throws Exception{
		h.beforeSuite();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("App is Launched and searching for online Devices...");
		List<String> initialOnlineDevicesCheck = new ArrayList<String>();
		List<String> afterRelaunchOnlineDevices = new ArrayList<String>();
		//Thread.sleep(5000);
		h.channelsList();
		if (channelsList.size()==0) {
			Assert.fail("No channels in the list");
		}
		
		for (int i = 0; i < channelsList.size(); i++) {
			h.channelsList();
			if (channelsList.get(i).isEnabled()==true) {
				if (channelsList.get(i).isDisplayed()==false) {
					initialOnlineDevicesCheck.add(channelsList.get(i).findElements(By.className("UIAStaticText")).get(2).getAttribute("name"));
				} else {
					initialOnlineDevicesCheck.add(channelsList.get(i).getAttribute("name"));
				}
			}
			
		}
//		driver.closeApp();
		driver.quit();
		System.out.println("Closing the App session and Re launching the App again");
//		driver.launchApp();
		h.beforeSuite();
		//Thread.sleep(5000);
		h.channelsList();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Assert.assertTrue(channelsList.size()!=0, "No devices in the network after relaunching the App");
		for (int i = 0; i < channelsList.size(); i++) {
			h.channelsList();
			//Thread.sleep(3000);
			if (channelsList.get(i).isEnabled()==true){
				if (channelsList.get(i).isDisplayed()==false) {
					afterRelaunchOnlineDevices.add(channelsList.get(i).findElements(By.className("UIAStaticText")).get(2).getAttribute("name"));
				} else {
					afterRelaunchOnlineDevices.add(channelsList.get(i).getAttribute("name"));
				}
			}
		}
		Assert.assertEquals(initialOnlineDevicesCheck.size(), afterRelaunchOnlineDevices.size());
		Collections.sort(initialOnlineDevicesCheck);
		Collections.sort(afterRelaunchOnlineDevices);
		System.out.println(initialOnlineDevicesCheck.equals(afterRelaunchOnlineDevices));
		System.out.println("Devices discovered correctly after re lauching the App");
		System.out.println("******************************************************");
//		driver.closeApp();
//		driver.launchApp();
		driver.quit();
		
	}
	

	/**********************************************************************************************
	 ***** Verifying Online/Offline Devices by Launching the App, Closing and Re launching the App.
	 * @throws Exception 
	 **********************************************************************************************/
	@Test(priority=2)
	public void onlineOflineCheck() throws Exception{
		h.beforeSuite();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<String> onlineDevicesCheck = new ArrayList<String>();
		List<String> offlineDevicesCheck = new ArrayList<String>();
		System.out.println("App is launched and Searching for Online and Offline Devices...");
		//Thread.sleep(6000);
		h.channelsList();
		System.out.println("Segregating Online and Offline devices...");
		for (int i = 0; i < channelsList.size(); i++) {
			h.channelsList();
			//Thread.sleep(4000);
			if (channelsList.get(i).isEnabled()==true) {
				if (channelsList.get(i).isDisplayed()==false) {
					onlineDevicesCheck.add(channelsList.get(i).findElements(By.className("UIAStaticText")).get(2).getAttribute("name"));
				} else {
					onlineDevicesCheck.add(channelsList.get(i).getAttribute("name"));
				}
			} else {
				if (channelsList.get(i).isDisplayed()==false) {
					offlineDevicesCheck.add(channelsList.get(i).findElements(By.className("UIAStaticText")).get(2).getAttribute("name"));
				} else {
					offlineDevicesCheck.add(channelsList.get(i).getAttribute("name"));
				}
			}
		}
		System.out.println("Online Devices are: "+onlineDevicesCheck);
		System.out.println("Offline Devices are: "+offlineDevicesCheck);
		System.out.println("Closing the session and relaunching App...");
		//driver.closeApp();
		driver.quit();
		h.beforeSuite();
		//driver.launchApp();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Thread.sleep(5000);
		System.out.println("App launched Again");
		Assert.assertTrue(channelsList.size()!=0, "NO Devices Found after re launching the App");
		List<String> reLaunchOnlineDevicesCheck = new ArrayList<String>();
		List<String> reLaunchOfflineDevicesCheck = new ArrayList<String>();
		System.out.println("Segregating Online and offline devices after re launching the App.... and checking these with initial Devices...");
		h.channelsList();
		for (int i = 0; i < channelsList.size(); i++) {
			h.channelsList();
			if (channelsList.get(i).isEnabled()==true) {//checking whether channel is offline or online(true = online & false = offline)
				if (channelsList.get(i).isDisplayed()==false) {// checking whether channel hidden in scroll list or not
					reLaunchOnlineDevicesCheck.add(channelsList.get(i).findElements(By.className("UIAStaticText")).get(2).getAttribute("name"));
				} else {
					reLaunchOnlineDevicesCheck.add(channelsList.get(i).getAttribute("name"));
				}
			} else {
				if (channelsList.get(i).isDisplayed()==false) {
					reLaunchOfflineDevicesCheck.add(channelsList.get(i).findElements(By.className("UIAStaticText")).get(2).getAttribute("name"));
				} else {
					reLaunchOfflineDevicesCheck.add(channelsList.get(i).getAttribute("name"));
				}
			}
		}
		System.out.println("Online Devices after relaunch: "+reLaunchOnlineDevicesCheck);
		System.out.println("Offline devices after relaunch: "+reLaunchOfflineDevicesCheck);
		
		Assert.assertTrue(onlineDevicesCheck.size()==reLaunchOnlineDevicesCheck.size(), "Number of Online devices are not same after re launching the App");
		Assert.assertTrue(offlineDevicesCheck.size()==reLaunchOfflineDevicesCheck.size(), "Number of Offline devices are not same after re launching the App");
		Collections.sort(onlineDevicesCheck);Collections.sort(offlineDevicesCheck);Collections.sort(reLaunchOfflineDevicesCheck);Collections.sort(reLaunchOnlineDevicesCheck);
		boolean online = onlineDevicesCheck.equals(reLaunchOnlineDevicesCheck);
		boolean offline = offlineDevicesCheck.equals(reLaunchOfflineDevicesCheck);
		if (online==true) {
			System.out.println("**** Online Devices are same after launching, closing and re launching the App **** ");
		} else {
			Assert.fail(" **** Online Devices are not same after re launching the App **** ");
		}
		if (offline==true) {
			System.out.println("**** Offline Devices are same after launching, closing and re launching the App **** ");
		} else {
			Assert.fail(" **** Offline Devices are not same after re launching the App **** ");
		}
		
	}
	
	/**************************************************************************************************
	 ****************** Cleaning up offline devices from the Channels list **************************** 
	 * @throws Exception 
	 * 
	 **************************************************************************************************/
	
	@Test(priority=3)
	public void removeOfflineDevices() throws Exception{
		System.out.println("Getting offline Devices and removing them from the Channels list...");
		Thread.sleep(2000);
		h.channelsList();
		List<WebElement> offlineDevicesToRemove = new ArrayList<WebElement>();
		List<WebElement> offlineDevicesAfterRemove = new ArrayList<WebElement>();
		if (channelsList.size()!=0) {
			for (int i = 0; i < channelsList.size(); i++) {
				h.channelsList();
				if (channelsList.get(i).isEnabled()==false) {
					offlineDevicesToRemove.add(channelsList.get(i));
				}
			}
			int offlineDevicesCount=offlineDevicesToRemove.size();
			if (offlineDevicesCount!=0) {
				System.out.println("There are"+" "+offlineDevicesToRemove.size()+" "+"Offline Devices");
				System.out.println("Refreshing the Channels list by swipe down the list");
				driver.swipe(0, 94, 0, 886, 2000);
				System.out.println("Searching for offline devices after refresh");
				for (int i = 0; i < channelsList.size(); i++) {
					h.channelsList();
					if (channelsList.get(i).isEnabled()==false) {
						offlineDevicesAfterRemove.add(channelsList.get(i));
					}
				}
				int offlineDevicesAfterRemoveCount = offlineDevicesAfterRemove.size();
				Assert.assertTrue(offlineDevicesCount!=offlineDevicesAfterRemoveCount, "Offline Devices are not removed from channels list after swipe down the channels list");
				System.out.println("**** Offline devices removed from list");
			} else {
				System.out.println("No offline devices in the list");
			}
		}
		else {
			System.out.println("No Devices in the list");
		}
//		driver.closeApp();
//		driver.launchApp();
		driver.quit();
		h.beforeSuite();
	}
	
}

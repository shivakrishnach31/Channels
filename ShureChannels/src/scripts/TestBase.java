package scripts;

import java.util.List;
import java.util.Properties;
import java.util.Random;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeSuite;

import testUtils.Helper;

public class TestBase {
	public static AppiumDriver driver ;
	public static Properties or,config ;
	public static Helper h;
	public static List<WebElement> channelsList;
	public static Random random;
	public static List<WebElement> demoChannelsList;
	
	
	
	@BeforeSuite
	public void beforeSuite() throws Exception {
		or = new Properties();
		config = new Properties();
		h = new Helper();
		random = new Random();
		
		h.setCaps(); // setting capabilities
		//h.resetToDefaults();
		h.channelsList(); // storing channels list
	}

}

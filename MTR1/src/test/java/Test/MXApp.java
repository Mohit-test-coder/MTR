package Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Capabilites.MX;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class MXApp extends MX {
	public void goBack() {
	 driver.pressKey(new KeyEvent(AndroidKey.BACK));
}
AndroidDriver<AndroidElement> driver;
	
	@BeforeTest
	public void SteUp() throws IOException, InterruptedException {
		service = startServer();
		driver = Cap(DeviceName, platformName, appPackage, appActivity);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	@Test(priority = 0)
    public void testgeneralStore() throws InterruptedException {
		//Checking if the app is opening
        System.out.println("GeneralStore is Opening");
        
    }
	
	@Test(priority = 1)
	public void Touch() {
		
		//Click on the ShareFile
		AndroidElement toch = driver.findElement(MobileBy.id("com.mxtech.videoplayer.ad:id/tv_tile"));
		toch.click();

		// Click on Share 
		AndroidElement share = driver.findElement(MobileBy.id("com.mxtech.videoplayer.ad.tr:id/text1"));
		share.click();
		
		//Back btn
		goBack();
		
		//Back btn
		goBack();
		
		
       // Click on the profile 
		AndroidElement me = driver.findElement(MobileBy.id("com.mxtech.videoplayer.ad:id/iv_me"));	
		me.click();
		

		
	}
	
	@Test(priority = 2)
	public void scrolll() {
		
		//Click on the Legal
		AndroidElement legal = driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Legal\")"));
		legal.click();
		
		//Click on the Privacy policy
		AndroidElement policy = driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Privacy Policy\")"));
		policy.click();
		
		// Scrolling to the bottom
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"grievanceofficer-amazon-MX-Player@amazon.com\"))");
		
		
		//Back btn
		goBack();
		
		//Back btn
		goBack();
		
	}
	
	@Test(priority = 3)
	public void click() {
		
		//Click on Settings
		AndroidElement settingMob = driver.findElement(MobileBy.xpath("//android.widget.TextView[@text=\"Settings\"]"));
		settingMob.click();
		
		//Click on general
		AndroidElement generals = driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"General\")"));
		generals.click();
		
		//Scroll down
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Import\"))");
		
		//Back btn
		goBack();
		
		//Back btn
		goBack();
	}
	
	@Test(priority = 4)
	public void send() {
		
		//Back to the main page
		AndroidElement filess = driver.findElement(MobileBy.xpath("//android.widget.TextView[@text=\"Local\"]"));
		filess.click();
		
		//click on search
		AndroidElement src = driver.findElement(MobileBy.AccessibilityId("Search"));
		src.click();
		
		//search files
		AndroidElement type = driver.findElement(MobileBy.id("com.mxtech.videoplayer.ad:id/search_src_text"));
		type.click();
		type.sendKeys("Priates of Mumbai");
		
		//clearing the text
		AndroidElement cross = driver.findElement(MobileBy.AccessibilityId("Clear query"));
		cross.click();
		
		//closing the search bar
		AndroidElement done = driver.findElement(MobileBy.AccessibilityId("Done"));
		done.click();
		
		//Stopping the server 
		service.stop();
		
	}
	
	

}

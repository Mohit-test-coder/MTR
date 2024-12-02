package Capabilites;

import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class MX {
	
	 // Variables to hold device and app configuration values
    public static String DeviceName;
    public static String platformName;
    public static String appPackage;
    public static String appActivity;
	

 // Starting the Appium server
 	public AppiumDriverLocalService service;
 	 //for starting the server
 	 public AppiumDriverLocalService startServer()
 	 {
 	 boolean flag = checkifserverisRunning(4723);
 	 if(!flag)
 	 {
 	 service = AppiumDriverLocalService.buildDefaultService();
 	 service.start();
 	 }
 	 return service;
 	 }
 	 public static boolean checkifserverisRunning(int port)
 	 {
 	 boolean isServerRunning=false;
 	 ServerSocket serversocket;
 	 try{
 	 serversocket = new ServerSocket(port);
 	 serversocket.close();
 	 }
 	 catch(IOException e)
 	 {
 	 isServerRunning = true;
 	 }
 	 finally {
 	 serversocket=null;
 	 }
 	 return isServerRunning;
 	 }
	
	// To run our emulator in the ide or project we use this method in the capabilites file
    
  
	public static void StartEmulator() throws IOException, InterruptedException {
    	
        // Execute a batch file to start the Android emulator
        Runtime.getRuntime().exec(System.getProperty("user.dir") + 
        		"//src//test//resources//RadhaRani.bat");
        
        // Wait for the emulator to fully boot up (9 seconds in this case)
        Thread.sleep(19000);
    }
		
   
		// Method to set up Appium capabilities and return an AndroidDriver instance
    public static AndroidDriver<AndroidElement> Cap(String DeviceName, String platformName, String appPackage, String appActivity)
            throws IOException, InterruptedException {
	    	
    	// Load global configuration from the properties file
        FileReader reader = new FileReader(System.getProperty("user.dir") + "//src//main//java//Global.properties");
        Properties pro = new Properties();
        pro.load(reader);

        // Retrieve properties from the file and assign to variables
        DeviceName = pro.getProperty("deviceName");
        platformName = pro.getProperty("platformName");
        appPackage = pro.getProperty("appPackage");
        appActivity = pro.getProperty("appActivity");

        // Check if the device name is "emulator" and start the emulator if true
        if (DeviceName.equals("emulator")) {
            StartEmulator();
        }

        // Create a DesiredCapabilities object to set app and device configurations
        DesiredCapabilities dc = new DesiredCapabilities();

        // Set the name of the device (e.g., emulator or a physical device name)
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, DeviceName);

        // Specify the platform as Android
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);

        // Set the app package (unique identifier for the app)
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);

        // Set the app activity (main entry point of the app)
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);

        // Prevent Appium from resetting app data after each session
        dc.setCapability(MobileCapabilityType.NO_RESET, true);

        // Initialize AndroidDriver with the Appium server URL and desired capabilities
        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(
                new URL("http://0.0.0.0:4723/wd/hub"), dc);

        // Return the AndroidDriver instance for running tests
        return driver;
    }

}

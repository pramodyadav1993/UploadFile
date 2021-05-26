package MavenSelenium.MavenSelenium;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FileUpload 
{
	public static final String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
	
	public static final String AUTOMATE_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	  ;
	public static void main(String[] args) throws Exception 
	{
		
		//System.out.println(System.getenv("BROWSERSTACK_USERNAME"));
		DesiredCapabilities caps=new DesiredCapabilities();
		caps.setCapability("os_version", "10");
		caps.setCapability("browser", "Chrome");
	    caps.setCapability("browser_version", "latest");
	    caps.setCapability("os", "Windows");
	    caps.setCapability("name", "Maven Sample Test"); // test name
	    WebDriver driver=new RemoteWebDriver(new URL(URL), caps);
	    driver.manage().window().maximize();
	    ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
	    driver.get("https://docupub.com/pdfcompress/");
	    driver.findElement(By.id("doc_file")).sendKeys("C:\\Users\\PRAMOD YADAV\\Downloads\\document.pdf");
	    WebDriverWait wait = new WebDriverWait(driver, 5);
	    JavascriptExecutor jse = (JavascriptExecutor)driver;
    	try {
			wait.until(ExpectedConditions.titleContains("Compress PDF online for free"));
			jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Title matched!\"}}");
    	}
    	catch(Exception e) {
    		jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Title not matched\"}}");
    	}
	    System.out.println(driver.getTitle());
	    
	    
	    driver.quit();
	}
}

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
		String username = System.getenv("BROWSERSTACK_USERNAME");
		String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
		String buildName = System.getenv("BROWSERSTACK_BUILD_NAME");
		String browserstackLocal = System.getenv("BROWSERSTACK_LOCAL");
		String browserstackLocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("os", "Windows");
		capabilities.setCapability("os_version", "10");
		capabilities.setCapability("browser", "chrome");
		capabilities.setCapability("browser_version", "latest");
		capabilities.setCapability("name", "BStack-[Java] Sample Test"); // test buildName
		capabilities.setCapability("build", buildName); // CI/CD job name using BROWSERSTACK_BUILD_NAME env variable
		//capabilities.setCapability("browserstack.local", browserstackLocal);
		//capabilities.setCapability("browserstack.localIdentifier", browserstackLocalIdentifier);
		WebDriver driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + "@hub.browserstack.com/wd/hub"), capabilities);
		
		
		
		
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

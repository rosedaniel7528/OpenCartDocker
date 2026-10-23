package testComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BaseTest {

	protected WebDriver driver;
	@Parameters({"browserName"})
	@BeforeMethod
	public WebDriver initilize(ITestContext context,String browserName) {

		String browser = browserName!=null?browserName:"chrome";
		String platform = System.getProperty("platform")!=null?System.getProperty("platform"):"local";


		if(browser.equalsIgnoreCase("chrome")){
			if(platform.equalsIgnoreCase("local")){

				ChromeOptions option = new ChromeOptions();
				option.setExperimentalOption("excludeSwitches",
						Arrays.asList("disable-popup-blocking"));
				//option.addArguments("--remote-allow-origins=*");
				driver = new ChromeDriver(option);
				driver.get("https://awesomeqa.com/ui/index.php?route=common/home");
				driver.manage().window().maximize();
				context.setAttribute("WebDriver",driver);
				return driver;
			} else if (platform.equalsIgnoreCase("remote")) {

				ChromeOptions option = new ChromeOptions();
				option.setExperimentalOption("excludeSwitches",
						Arrays.asList("disable-popup-blocking"));
				option.setPlatformName("linux");
				option.setPageLoadStrategy(PageLoadStrategy.EAGER);
				try {
					driver = new RemoteWebDriver(new URL("http://localhost:4444"), option);
				}catch (MalformedURLException e){
					throw new RuntimeException(e);
				}
				driver.get("https://awesomeqa.com/ui/index.php?route=common/home");
				driver.manage().window().maximize();
				context.setAttribute("WebDriver",driver);
				return driver;
			}
			System.out.println("Platform name entered is not supported");
			return null;

		} else if (browser.equalsIgnoreCase("firefox")) {

			if(platform.equalsIgnoreCase("local")){
				FirefoxOptions option = new FirefoxOptions();
				//option.addArguments("--remote-allow-origins=*");
				driver = new FirefoxDriver(option);
				driver.get("https://awesomeqa.com/ui/index.php?route=common/home");
				driver.manage().window().maximize();
				context.setAttribute("WebDriver",driver);
				return driver;
			}else if (platform.equalsIgnoreCase("remote")) {

				FirefoxOptions option = new FirefoxOptions();
				option.setPlatformName("linux");
				option.setPageLoadStrategy(PageLoadStrategy.EAGER);
				try {
					driver = new RemoteWebDriver(new URL("http://localhost:4444"), option);
				}catch (MalformedURLException e){
					throw new RuntimeException(e);
				}
				driver.get("https://awesomeqa.com/ui/index.php?route=common/home");
				driver.manage().window().maximize();
				context.setAttribute("WebDriver",driver);
				return driver;
			}
			System.out.println("Platform name entered is not supported");
			return null;

		}else {
			System.out.println("Browser name entered is not supported");
			return null;
		}
	}

	@AfterMethod
	public void quit() {
		driver.quit();
	}

	public List<HashMap<String, String>> dataReader(String path) throws IOException {

		// converting jason file into string
		String jasonStringfile = new String(Files.readAllBytes(Paths.get(path)));
		
	//	String jasonStringfile = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
		// converting String into HasMap using Jakson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> map = mapper.readValue(jasonStringfile,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return map;
	}
	
	    public String getScreenshot(String testname, WebDriver driver2) throws IOException {
		TakesScreenshot take =(TakesScreenshot)driver2;
		File source =take.getScreenshotAs(OutputType.FILE);
		File picture = new File(System.getProperty("user.dir")+"\\reports\\"+testname+".png");
		System.out.println(System.getProperty("user.dir")+"\\reports\\"+testname+".png");
		FileUtils.copyFile(source,picture);
		return System.getProperty("user.dir")+"\\reports\\"+testname+".png";
	}
}

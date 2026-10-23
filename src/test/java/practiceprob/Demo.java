package practiceprob;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Demo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		
		driver.get(null);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement  web = driver.findElement(By.xpath(null));
		
		web.getRect().getDimension().getHeight();
		
		Select sel = new Select(driver.findElement(By.xpath("static dropdown")));
		sel.selectByIndex(0);
		
		List<WebElement> ele = new ArrayList<>();
		
		ele =driver.findElements(By.xpath(null));
		
		for(WebElement e:ele) {
			if(e.getText().equalsIgnoreCase("text")) {
				//nothing
			}
		}
		
		driver.switchTo().alert().accept();
		
		
		Actions act = new Actions(driver);
		
		act.moveToElement(null).click().build().perform();
		
		Set<String> hand =driver.getWindowHandles();
		Iterator<String> it = hand.iterator();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		js.executeAsyncScript("window.scrollBy(0,500)");
		
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(src, new File("screenshot.png"));
		
		wait.until(ExpectedConditions.invisibilityOf(web));
		
		
		
		
		
		
		
	}

}

package SeleniumAutomation.Opencart;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjectModel.CartPage;
import pageObjectModel.Cartdialoguebox;
import pageObjectModel.CheckoutPage;
import pageObjectModel.HomePage;
import pageObjectModel.OrderSuccessPage;
import pageObjectModel.ProductList;
import pageObjectModel.SearchResult;
import testComponents.BaseTest;

public class SIT extends BaseTest{

	@Test(dataProvider="readData",retryAnalyzer = testComponents.Retry.class)
    public void placeorder(HashMap<String,String> data) throws InterruptedException {
		
		// code
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		String productName = data.get("product");
		//home page
		HomePage homepage= new HomePage(driver);
		homepage.clickMenu(); //select category
		//product list menu
		ProductList productlist= new ProductList(driver);
		productlist.selectProduct(productName); // select product move to cart
		//cart dialogue box
		Cartdialoguebox box= new Cartdialoguebox(driver);
		box.clickChekout();
		//cart page
		CartPage cartpage= new CartPage(driver);
		cartpage.checkproductincart(productName); //verifying the product in cart
		//checkout page
		CheckoutPage checkout =new CheckoutPage(driver);
		checkout.fillCheckoutDetails();
		//order success page
		OrderSuccessPage success = new OrderSuccessPage(driver);
		success.orderconfirmation(); //Verifying success msg
    }
	
	
	@Test(dataProvider = "searchboxdata", retryAnalyzer = testComponents.Retry.class)
	public void searchBox(HashMap<String,String> data) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String productname = data.get("product");
		//home page
		HomePage homepage = new HomePage(driver);
		homepage.searchbox(productname);
		//product list
		SearchResult searchresult = new SearchResult(driver);
		searchresult.selectproduct(productname);
		//cart dialogue box
		Cartdialoguebox box= new Cartdialoguebox(driver);
		box.clickChekout();
		//cart page
		CartPage cartpage= new CartPage(driver);
		cartpage.checkproductincart(productname); //verifying the product in cart
		//checkout page
		CheckoutPage checkout =new CheckoutPage(driver);
		checkout.fillCheckoutDetails();
		//order success page
		OrderSuccessPage success = new OrderSuccessPage(driver);
		success.orderconfirmation(); //Verifying success msg
	}
	
	@Test
	public void javactions() {
		HomePage homepage= new HomePage(driver);
		homepage.clickdesktop();
	}
	
	@Test
	public void brokenLinks() throws IOException {
		HomePage homepage= new HomePage(driver);
			homepage.brokenlinks();
	}
	
	@DataProvider
	public Object[][] readData() throws IOException {
		List<HashMap<String, String>> data =dataReader(System.getProperty("user.dir") + "//src//test//java//testdata//Sit.json");
		return new Object[][] {{data.get(0)}};
	}
	
	@DataProvider
	public Object[][] searchboxdata() throws IOException {
		List<HashMap<String, String>> data = dataReader(System.getProperty("user.dir") + "//src//test//java//testdata//searchbox.json");
		return new Object[][] {{data.get(0)}};
	}
}

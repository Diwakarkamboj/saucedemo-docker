package saucedemotestfiles;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import baseTestPackage.BaseTest;
import pageobjects.CartPage;
import pageobjects.CheckOutOverview;
import pageobjects.CheckOutPage;
import pageobjects.ProductsPage;
import pageobjects.SwagLabsLoginPage;
import saucedemotestdata.SauceLabTestData;
import utils.Config;
import utils.Constants;
import utils.JsonUtil;

public class SauceLabTest extends BaseTest{

	ProductsPage prodPage;
	CartPage cartPage;
	CheckOutPage checkOutPage;
	CheckOutOverview checkoutOverview;
	
	private SauceLabTestData testData;
	
	@BeforeTest
	@Parameters("testDataPath")
	public void setParameters(String testDataPath) {
		this.testData = JsonUtil.getTestData(testDataPath, SauceLabTestData.class);
	}
	
	@Test
	public void validUserData() {
		SwagLabsLoginPage swagLabLoginPage = new SwagLabsLoginPage(driver);
		swagLabLoginPage.goTo(Config.get(Constants.SAUCELAB_URL));
		swagLabLoginPage.isAt();
		swagLabLoginPage.userLogin(testData.username(), testData.password());
	}
	
	@Test(dependsOnMethods = "validUserData")
	public void addProductsToCart() throws InterruptedException {
		prodPage = new ProductsPage(driver);
		prodPage.isAt();
		prodPage.addProductToCart();
		prodPage.clickCartIcon();
	}
	
	@Test(dependsOnMethods = "addProductsToCart")
	public void verifyCartItems() {
		cartPage = new CartPage(driver);
		cartPage.isAt();
		AssertJUnit.assertTrue(cartPage.checkCartQuantity()==prodPage.productsToAdd.size());
		cartPage.clkCheckoutBtn();
	}
	
	@Test(dependsOnMethods = "verifyCartItems")
	public void vfyErrorMessage() {
		checkOutPage = new CheckOutPage(driver);
		checkOutPage.isAt();
		AssertJUnit.assertTrue(checkOutPage.vfyErrorMessage().equalsIgnoreCase(testData.errorMessage()));
		checkOutPage.addAddressDetails(testData.firstName(),testData.lastName(), testData.postCode());
	}
	
	@Test(dependsOnMethods = "vfyErrorMessage")
	public void vfyTotalAmt() {
		checkoutOverview = new CheckOutOverview(driver);
		AssertJUnit.assertTrue(checkoutOverview.totalAmt().equalsIgnoreCase(testData.totalAmount()));
	}
	
	@Test(dependsOnMethods = "vfyTotalAmt")
	public void vfyOrderPlaceMsg() {
		checkoutOverview = new CheckOutOverview(driver);
		checkoutOverview.clkFinishBtn();
		AssertJUnit.assertTrue(checkoutOverview.orderPlacedMsg().equalsIgnoreCase(testData.orderPlaceMsg()));
		AssertJUnit.assertTrue(checkoutOverview.dispatchMsg().equalsIgnoreCase(testData.disptachedMsg()));

	}
}

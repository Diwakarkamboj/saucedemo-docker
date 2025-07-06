package pageobjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import saucedemoabstractionbase.AbstractBase;

public class ProductsPage extends AbstractBase{

	@FindBy(xpath="//div[@class='product_label']")
	WebElement productPage;
	
	@FindBy(xpath="//div[@class='inventory_item_name']")
	List<WebElement> productsList;
	
	@FindBy(xpath = "//button[text()='ADD TO CART']")
	WebElement addToCartBtn;
	
	@FindBy(xpath="//button[@class='inventory_details_back_button']")
	WebElement backBtn;
	
	@FindBy(xpath="//*[name()='svg'][ancestor::*[@id='shopping_cart_container']]")
	WebElement cartBtn;
	
	public List<String> productsToAdd = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt", "Sauce Labs Bike Light");

	public ProductsPage(WebDriver driver) {
		super(driver);
	}
	

	@Override
	public boolean isAt() {
		wait.until(ExpectedConditions.visibilityOf(productPage));
		return this.productPage.isDisplayed();
	}
	
	public void addProductToCart() throws InterruptedException {
		for(WebElement product : this.productsList) {
			if(productsToAdd.contains(product.getText()))
			{
				product.click();
				wait.until(ExpectedConditions.visibilityOf(addToCartBtn));
				this.addToCartBtn.click();
				wait.until(ExpectedConditions.visibilityOf(backBtn));
				this.backBtn.click();
			}
			
		}
	}
	
	public void clickCartIcon() {
		wait.until(ExpectedConditions.visibilityOf(cartBtn));
		this.cartBtn.click();
	}
	
	
	

}

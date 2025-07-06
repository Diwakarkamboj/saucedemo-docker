package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import saucedemoabstractionbase.AbstractBase;


public class CartPage extends AbstractBase {

	public CartPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//div[text()='Your Cart']")
	WebElement yourCartText;

	@FindBy(xpath = "//*[@class='cart_quantity']")
	List<WebElement> cartQuantities;
	
	@FindBy(xpath="//a[text()='CHECKOUT']")
	WebElement checkoutBtn;

	private int totalQuantity = 0;

	@Override
	public boolean isAt() {
		wait.until(ExpectedConditions.visibilityOf(yourCartText));
		return this.yourCartText.isDisplayed();
	}
	
	
	public int checkCartQuantity() {

		for (WebElement cartQuantity : cartQuantities) {
			int quantity = Integer.parseInt(cartQuantity.getText());
			totalQuantity += quantity;
		}
		return totalQuantity;

	}
	
	public void clkCheckoutBtn() {
		checkoutBtn.click();
	}

	

}

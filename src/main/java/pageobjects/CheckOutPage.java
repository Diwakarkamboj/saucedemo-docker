package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import saucedemoabstractionbase.AbstractBase;

public class CheckOutPage extends AbstractBase{

	public CheckOutPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//div[text()='Checkout: Your Information']")
	WebElement checkoutPageText;
	
	@FindBy(xpath="//input[@id='first-name']")
	WebElement firstNameField;
	
	@FindBy(xpath="//input[@id='last-name']")
	WebElement lastNameField;
	
	@FindBy(xpath="//input[@id='postal-code']")
	WebElement postCodeField;
	
	@FindBy(xpath="//input[@value='CONTINUE']")
	WebElement continueBtn;
	
	@FindBy(xpath = "//*[text()='Error: ']")
	WebElement errorMsg;
	
	@Override
	public boolean isAt() {
		wait.until(ExpectedConditions.visibilityOf(checkoutPageText));
		return this.checkoutPageText.isDisplayed();
	}
	
	public String vfyErrorMessage() {
		this.continueBtn.click();
		return this.errorMsg.getText();
	}
	
	public void addAddressDetails(String firstName, String lastName, String postCode) {
		this.firstNameField.sendKeys(firstName);
		this.lastNameField.sendKeys(lastName);
		this.postCodeField.sendKeys(postCode);
		this.continueBtn.click();
	}

}

package pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import saucedemoabstractionbase.AbstractBase;

public class SwagLabsLoginPage extends AbstractBase {
	
	public SwagLabsLoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='user-name']")
	WebElement usrnameField;
	
	@FindBy(xpath="//input[@id='password']")
	WebElement pwdField;

	@FindBy(xpath="//input[@id='login-button']")
	WebElement loginBtn;
	
	@FindBy(xpath="//div[@class='login_logo']")
	WebElement swagLogo;
	

	@Override
	public boolean isAt() {
		
		wait.until(ExpectedConditions.visibilityOf(this.swagLogo));
		return this.swagLogo.isDisplayed();
	}
	
	public void goTo(String url) {
		driver.get(url);
	}
	
	public void userLogin(String username, String password) {
		this.usrnameField.sendKeys(username);
		this.pwdField.sendKeys(password);
		this.loginBtn.click();
		
	}
	
}

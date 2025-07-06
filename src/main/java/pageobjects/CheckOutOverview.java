package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import saucedemoabstractionbase.AbstractBase;

public class CheckOutOverview extends AbstractBase{

	public CheckOutOverview(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[text()='Checkout: Overview']")
	WebElement checkoutOverviewText;
	
	@FindBy(xpath="//*[@class='summary_total_label']")
	WebElement totalAmt;
	
	@FindBy(xpath="//*[text()='FINISH']")
	WebElement finishBtn;
	
	@FindBy(xpath="//*[@class='complete-header']")
	WebElement orderPlacedMsg;
	
	@FindBy(xpath="//*[@class='complete-text']")
	WebElement completeTextMsg;
	
	@Override
	public boolean isAt() {
		wait.until(ExpectedConditions.visibilityOf(checkoutOverviewText));
		return this.checkoutOverviewText.isDisplayed();
	}
	
	public String totalAmt() {
		return this.totalAmt.getText();
	}
	
	public void clkFinishBtn() {
		this.finishBtn.click();
	}
	
	public String orderPlacedMsg() {
		return this.orderPlacedMsg.getText();
	}
	
	public String dispatchMsg() {
		return this.completeTextMsg.getText();
	}

}

package saucedemoabstractionbase;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractBase {
	
	protected final WebDriver driver;
	protected final WebDriverWait wait;
	
	public AbstractBase(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	public abstract boolean isAt();
	

}

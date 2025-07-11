package baseTestPackage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import io.github.bonigarcia.wdm.WebDriverManager;
import listenerfile.Listener;
import utils.Config;
import utils.Constants;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;

@Listeners({ Listener.class })
public abstract class BaseTest {

	private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

	protected WebDriver driver;

	@BeforeSuite
	public void setupConfig() {
		Config.initialized();
	}

	@BeforeTest
	// @Parameters({"browser"}) //TO CHANGE BROWSER FOR EVERY TEST
	// public void setDriver(String browser, ITestContext ctx) throws
	// MalformedURLException {
	public void setDriver(ITestContext ctx) throws MalformedURLException {

		this.driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED)) ? getRemoteDriver() : getLocalDriver();
		ctx.setAttribute(Constants.DRIVER, this.driver);
		this.driver.manage().window().maximize();

	}

	private WebDriver getRemoteDriver() throws MalformedURLException {

		Capabilities capabilities = new ChromeOptions();
		if (Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))) {

			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("-private");
			capabilities = new FirefoxOptions(options);
		}

		String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
		String hubHost = Config.get(Constants.GRID_HUB_HOST);
		String url = String.format(urlFormat, hubHost);

//		if(System.getProperty("browser").equalsIgnoreCase("chrome"))
//		if(browser.equalsIgnoreCase("chrome"))
//		{
//			capabilities = new ChromeOptions();
//		}else 
//		{
//			capabilities = new FirefoxOptions();
//		}

		log.info("grid url: {}", url);

		return new RemoteWebDriver(new URL(url), capabilities);

	}

	private WebDriver getLocalDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		WebDriverManager.chromedriver().browserVersion("127.0.6533.73").setup();
		return new ChromeDriver(options);

	}


	@AfterClass(alwaysRun = true)
	public void tearDown() {
		if (this.driver != null) {
			this.driver.quit();
		}
	}

}

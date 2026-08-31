package Com.DemoWebShop_Generic_Utility;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Com.DemoWebShop_POM_Utility.HomePage;
import Com.DemoWebShop_POM_Utility.LoginPage;
import Com.DemoWebShop_POM_Utility.WelcomePage;

public class BaseTest {

	public WebDriver driver;
	public static WebDriver sDriver;
	public ExtentSparkReporter spark;
	public ExtentReports reports;
	public ExtentTest test;
	public FileUtility fileUtility = new FileUtility();
	public WelcomePage welcomePage;
	public LoginPage loginPage;
	public HomePage homePage;
	public WebDriverUtility webDriverUtility = new WebDriverUtility();

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("@BeforeSuite  DataBase Connected");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("@BeforeTest  Report Started");

		spark = new ExtentSparkReporter(FrameWorkConstants.reportsPath);
		reports = new ExtentReports();
		reports.attachReporter(spark);
		test = reports.createTest("Address");

	}

	@BeforeClass
	public void beforeClass() throws IOException {
		System.out.println("@BeforeClass  Launch Browser");

//		String browser = fileUtility.readDataFromPropertyFile("browserName");
//		String url = fileUtility.readDataFromPropertyFile("url");

		String browser = System.getProperty("browserName");
		String url = System.getProperty("baseUrl");

		if (browser.equals("chrome")) {
			System.out.println("Chrome Launched");
			driver = new ChromeDriver();
		} else if (browser.equals("edge")) {
			System.out.println("Edge Launched");
			driver = new EdgeDriver();
		} else if (browser.equals("firefox")) {
			System.out.println("Firefox Launched");
			driver = new FirefoxDriver();
		} else {
			System.out.println("Invalid Browser name");
		}

		sDriver = driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(url);

	}

	@BeforeMethod
	public void beforeMethod() throws IOException {
		System.out.println("@BeforeMethod  Login");

		welcomePage = new WelcomePage(driver);
		welcomePage.getLoginBtn().click();

		loginPage = new LoginPage(driver);
		loginPage.getEmailTextField().sendKeys(fileUtility.readDataFromPropertyFile("username"));
		loginPage.getPasswordTextField().sendKeys(fileUtility.readDataFromPropertyFile("password"));
		loginPage.getLoginBtn().click();

		homePage = new HomePage(driver);

	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("@AfterMethod  Logout");
		homePage.getLogOutBtn().click();
	}

	@AfterClass
	public void afterClass() throws InterruptedException {
		System.out.println("@AfterClass Close Browser");
		Thread.sleep(3000);
		driver.quit();
	}

	@AfterTest
	public void afterTest() {
		System.out.println("@AfterTest  Report Ended");
		reports.flush();
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("@AfterSuite  Database Disconnected");
	}

}

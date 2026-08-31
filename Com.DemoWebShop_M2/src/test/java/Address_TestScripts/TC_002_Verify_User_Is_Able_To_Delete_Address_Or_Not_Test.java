package Address_TestScripts;

import java.io.IOException;

import org.testng.annotations.Test;

import Com.DemoWebShop_Generic_Utility.BaseTest;
import Com.DemoWebShop_POM_Utility.AddressesPage;

public class TC_002_Verify_User_Is_Able_To_Delete_Address_Or_Not_Test extends BaseTest {

	@Test
	public void deleteAddress() throws InterruptedException, IOException {
		System.out.println("Delete Address");
		webDriverUtility.javaScriptScrollTillElement(driver, homePage.getAddressesBtn());
		homePage.getAddressesBtn().click();

		AddressesPage addressesPage = new AddressesPage(driver);
		addressesPage.getDeleteBtn().get(0).click();

		Thread.sleep(2000);
		webDriverUtility.javaScriptAcceptConfirmPopup(driver);

		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		webDriverUtility.webPageScreenshot(driver);

	}

}

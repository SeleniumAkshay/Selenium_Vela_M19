package Address_TestScripts;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import Com.DemoWebShop_Generic_Utility.BaseTest;
import Com.DemoWebShop_POM_Utility.AddNewAddressPage;
import Com.DemoWebShop_POM_Utility.AddressesPage;

public class TC_001_Verify_User_Is_Able_To_Add_Address_Or_Not_Test extends BaseTest {

	@Test
	public void addAddress() throws EncryptedDocumentException, IOException, InterruptedException {
		System.out.println("Add Address");
		webDriverUtility.javaScriptScrollTillElement(driver, homePage.getAddressesBtn());
		homePage.getAddressesBtn().click();

		AddressesPage addressesPage = new AddressesPage(driver);
		addressesPage.getAddNewBtn().click();

		AddNewAddressPage newAddressPage = new AddNewAddressPage(driver);
		newAddressPage.getFirstNameTextField().sendKeys(fileUtility.readDataFromExcelFile("Sheet1", 1, 0));
		newAddressPage.getLastNameTextField().sendKeys(fileUtility.readDataFromExcelFile("Sheet1", 1, 1));
		newAddressPage.getEmailTextField().sendKeys(fileUtility.readDataFromExcelFile("Sheet1", 1, 2));

		webDriverUtility.selectByVisibleText(newAddressPage.getCountryDropDown(),
				fileUtility.readDataFromExcelFile("Sheet1", 1, 3));

		newAddressPage.getCityTextField().sendKeys(fileUtility.readDataFromExcelFile("Sheet1", 1, 4));
		newAddressPage.getAddresses1TextField().sendKeys(fileUtility.readDataFromExcelFile("Sheet1", 1, 5));
		newAddressPage.getZipCodeTextField().sendKeys(fileUtility.readDataFromExcelFile("Sheet1", 1, 6));
		newAddressPage.getPhoneNumberTextField().sendKeys(fileUtility.readDataFromExcelFile("Sheet1", 1, 7));
		newAddressPage.getSaveBtn().click();

		Thread.sleep(3000);
		webDriverUtility.webPageScreenshot(driver);

	}

}

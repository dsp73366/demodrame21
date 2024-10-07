package ContactTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import vtiger.genericUtility.BaseClass;
import vtiger.genericUtility.ExcelFileUtility;
import vtigerObjectRepository.ContactInfoPage;
import vtigerObjectRepository.ContactPage;
import vtigerObjectRepository.CreateContactPage;
import vtigerObjectRepository.HomePage;

@Listeners(vtiger.genericUtility.ListenersImplementation.class)
public class ToCreateContactTest extends BaseClass {
	
	@Test(groups = "smoke")
	public void toCreateContactTest_001() throws EncryptedDocumentException, IOException {
	
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();
		ContactPage cp= new ContactPage(driver);
		cp.getCreateContactIcon().click();
		CreateContactPage ccp= new CreateContactPage(driver);
		ExcelFileUtility eutil= new ExcelFileUtility();
		String lastname = eutil.toReadDataFromExcelFile("contacts", 1, 2);
		ccp.getNameTextBox().sendKeys(lastname);
		ccp.getSaveIcon().click();
		Assert.fail(); // to fail and to check for screenshot
		ContactInfoPage cip= new ContactInfoPage(driver);
		String LASTNAME = cip.getTextValidation().getText();
		
		/*if (LASTNAME.contains(lastname)) {
			System.out.println(LASTNAME + "......passed");
		} else {
			System.out.println(LASTNAME + "......failed");
		}*/
		Assert.assertTrue(LASTNAME.contains(lastname));
		
		
		
	}
	

}

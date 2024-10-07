package OrganizationTest;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import vtiger.genericUtility.BaseClass;
import vtiger.genericUtility.ExcelFileUtility;
import vtiger.genericUtility.JavaUtility;
import vtigerObjectRepository.CreateOrganizationPage;
import vtigerObjectRepository.HomePage;
import vtigerObjectRepository.OrganizationInfoPage;
import vtigerObjectRepository.OrganizationPage;

@Listeners(vtiger.genericUtility.ListenersImplementation.class)

public class ToCreateOrganizationTest extends BaseClass{

	@Test(groups = "Regression")
	public void toCreateOrganizationTest_001() throws EncryptedDocumentException, IOException{
		
		HomePage hp= new HomePage(driver);
		hp.getOrganizationsLink().click();
		OrganizationPage op =new OrganizationPage(driver);
		op.getCreateOrganizationIcon().click();
		CreateOrganizationPage cop= new CreateOrganizationPage(driver);
		/*Random r = new Random();
		int random = r.nextInt(999);*/
		ExcelFileUtility eutil= new ExcelFileUtility();
		JavaUtility jutil= new JavaUtility();

		String ORGANNAME = eutil.toReadDataFromExcelFile("organizations", 1, 2)+jutil.toGetRandomNumber();
		
		cop.getOrganizationnameTextBox().sendKeys(ORGANNAME);
		cop.getSaveIcon().click();
		
		OrganizationInfoPage oip= new OrganizationInfoPage(driver);
		String organname = oip.getOrganizationTextValidation().getText();
		/*if (organname.contains(ORGANNAME)) {
			System.out.println(ORGANNAME + "......passed");
		} else {
			System.out.println(ORGANNAME + "......failed");
		}*/
		
		Assert.assertTrue(organname.contains(ORGANNAME));
		
	}
}

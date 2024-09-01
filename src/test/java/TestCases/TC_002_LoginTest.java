package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import BaseClass.BaseClass;

public class TC_002_LoginTest extends BaseClass {

	@Test(groups= {"Sanity","Master"})
	public void test_login()
	{
		try
		{
		logger.info("**** Starting TC_OO2_LoginTest *****");
	
		HomePage hp=new HomePage(dr);
		hp.clickMyAccount();
		logger.info("Clicked on My Account");
		
		hp.clickLogin();
		logger.info("Clicked on Login Link");
		
		
		LoginPage lp=new LoginPage(dr);
		logger.info("Providing login details");
		
		lp.setEmail("TESTRAGHAVA16@TESTING.COM"); //valid email , get it from config.properties
		lp.setPassword("test@123"); // valid password, get it from config.properties
		lp.clickLogin();
		logger.info("clicked on Login button");
		
		MyAccountPage macc=new MyAccountPage(dr);
		boolean targetpage=macc.isMyAccountPageExists();
		Assert.assertEquals(targetpage,true,"Invalid login data");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("**** Finished TC_002_LoginTest *****");
		
	}
}

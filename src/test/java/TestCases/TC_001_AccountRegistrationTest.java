package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import PageObjects.AccountRegistrationPage;
import PageObjects.HomePage;
import BaseClass.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass{
	@Test(groups= {"Regression","Master"})
	public void test_account_Registration() throws InterruptedException
	{
		logger.debug("application logs......");
		logger.info("***  Starting TC_001_AccountRegistrationTest ***");
		try
		{
		HomePage hp=new HomePage(dr);
		hp.clickMyAccount();
		logger.info("Clicked on My account link");
		
		hp.clickRegister();
		logger.info("Clicked on register link");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(dr);
			
		logger.info("Providing customer data");
		
		regpage.setFirstName(RandomString(10).toUpperCase());
		
		regpage.setLastName(RandomString(10).toUpperCase());
		
		regpage.setEmail(RandomMail().toLowerCase());// randomly generated the email
		
		regpage.setTelephone(RandomNumber(10));
		
		String password=RandomString(7).toLowerCase()+RandomNumber(2)+RandomString(1).toUpperCase();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		
		regpage.clickContinue();
		logger.info("Clicked on continue");
		
		String confmsg=regpage.getConfirmationMsg();
		
		logger.info("Validating expected message");
		Assert.assertEquals(confmsg, "Your Account Has Been Created!","Test failed");
		
		}catch(Exception e)
		{
			logger.error("test failed");
			Assert.fail();
		}
		logger.info("***  Finished TC_001_AccountRegistrationTest ***");		
		}
	
	
}

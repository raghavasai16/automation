package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import BaseClass.BaseClass;
import Utilites.DataProviders;

public class TC_003_LoginDDT extends BaseClass {

	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class)
	public void test_loginDDT(String email, String password,String exp)
	{
		logger.info("*** Starting TC_003_LoginDDT******");
		try
		{
		HomePage hp=new HomePage(dr);
		hp.clickMyAccount();
		hp.clickLogin();
				
		LoginPage lp=new LoginPage(dr);
		
		lp.setEmail(email);
		lp.setPassword(password); 
		lp.clickLogin();
		
		MyAccountPage macc=new MyAccountPage(dr);
		boolean targetpage=macc.isMyAccountPageExists();
				
		if(exp.equals("Valid"))
		{
			if(targetpage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
				
		}
		
		if(exp.equals("Invalid"))
		{
			if(targetpage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
	
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("*** Finished TC_003_LoginDDT******");	
		
	}
	
}










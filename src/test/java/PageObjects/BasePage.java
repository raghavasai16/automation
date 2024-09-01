package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

	
	WebDriver dr;
	
	BasePage(WebDriver dr)
	{
		this.dr=dr;
		PageFactory.initElements(dr, this);
	}
}

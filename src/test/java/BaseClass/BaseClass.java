package BaseClass;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public Properties p;
	public TakesScreenshot takesScreenshot;
	public Logger logger;
	public static WebDriver dr;
	ChromeOptions cr;
	@BeforeClass
	@Parameters({"browser","os","environment"})
	public void Login(String browser,String OS,String env) throws IOException
	{
		p= new Properties();
		logger=LogManager.getLogger(this.getClass());
		FileReader f = new FileReader("./src//test//resources//config.properties");
		p.load(f);
		cr= new ChromeOptions();
		cr.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
		
		if(env.toLowerCase().equals("local"))
		{
			switch(browser.toLowerCase())
			{
			case"chrome":
				dr= new ChromeDriver(cr);
				break;
			case"edge":
				dr= new EdgeDriver();
				break;
			case"firefox":
				dr= new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid Browser");
				return;
			
			}
		}
		else
		{
			String URL="http://192.168.0.134:4444/wd/hub";
			/*DesiredCapabilities cap = new DesiredCapabilities();
			if(OS.toLowerCase().equals("window"))
			{
				cap.setPlatform(Platform.WIN11);
			}
			if(OS.toLowerCase().equals("mac"))
			{
				cap.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("Invalid paltform");
				return;
			}
			
			switch(browser.toLowerCase())
			{
			case"chrome":
				cap.setBrowserName("chrome");
				break;
			case"edge":
				cap.setBrowserName("edge");
				
				break;
			case"firefox":
				cap.setBrowserName("firefox");
				break;
			default:
				System.out.println("Invalid Browser");
				return;
			
			}
			dr= RemoteWebDriver(new URL(URL),cap)
			*/
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setCapability("browserVersion", "128");
			chromeOptions.setCapability("platformName", "Windows");
			// Showing a test name instead of the session id in the Grid UI
			chromeOptions.setCapability("se:name", "My simple test"); 
			// Other type of metadata can be seen in the Grid UI by clicking on the 
			// session info or via GraphQL
			chromeOptions.setCapability("se:sampleMetadata", "Sample metadata value"); 
			 dr = new RemoteWebDriver(new URL(URL), chromeOptions);
			 System.out.println("It is Started");
		}
		
		dr.manage().window().maximize();
		dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		dr.get("https://tutorialsninja.com/demo/");
		
		
	}
	
	public String RandomString(int number)
	{
		String randomString=RandomStringUtils.randomAlphabetic(number);
		return randomString;
	}
	
	public String RandomNumber(int number)
	{
		String randomString=RandomStringUtils.randomNumeric(number);
		return randomString;
	}
	public String RandomMail()
	{
		String randomString=RandomStringUtils.randomAlphabetic(5)
				;
		return randomString+"@gmail.com";
	}
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		takesScreenshot = (TakesScreenshot) dr;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File destination = new File( System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png");

		try {
			source.renameTo(destination);
		} catch (Exception e) {
			e.getMessage();
		}
		return destination.toString();

	}

}

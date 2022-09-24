package commonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil {
	//method for login
	public static boolean verifyLogin(String username,String password)
	{
		driver.findElement(By.xpath(config.getProperty("ObjUser"))).sendKeys(username);
		driver.findElement(By.xpath(config.getProperty("ObjPass"))).sendKeys(password);
		driver.findElement(By.xpath(config.getProperty("ObjLogin"))).click();
		String expected="adminflow";
		String actual =driver.getCurrentUrl();
		if(actual.toLowerCase().contains(expected.toLowerCase()))
		{
			Reporter.log("Login success::"+expected+"    "+actual,true);
			return true;
		}
		else
		{
			Reporter.log("Login Fail::"+expected+"    "+actual,true);
			return false;
		}
	}
	//method for click branches
	public static void clickBranches() throws Throwable
	{
		driver.findElement(By.xpath(config.getProperty("ObjBranches"))).click();
		Thread.sleep(4000);
	}
	//method for new branch creation
	public static boolean verifyNewBranch(String BranchName,String Address1,String Address2,String Address3,
			String Area,String ZipCode,String Country,String State,String City)throws Throwable
	{
		driver.findElement(By.xpath(config.getProperty("ObjNew"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(config.getProperty("ObjBranch"))).sendKeys(BranchName);
		driver.findElement(By.xpath(config.getProperty("ObjAddress1"))).sendKeys(Address1);
		driver.findElement(By.xpath(config.getProperty("ObjAddress2"))).sendKeys(Address2);
		driver.findElement(By.xpath(config.getProperty("ObjAddress3"))).sendKeys(Address3);
		driver.findElement(By.xpath(config.getProperty("ObjArea"))).sendKeys(Area);
		driver.findElement(By.xpath(config.getProperty("ObjZipcode"))).sendKeys(ZipCode);
		new Select(driver.findElement(By.xpath(config.getProperty("ObjCountry")))).selectByVisibleText(Country);
		new Select(driver.findElement(By.xpath(config.getProperty("ObjState")))).selectByVisibleText(State);
		new Select(driver.findElement(By.xpath(config.getProperty("ObjCity")))).selectByVisibleText(City);
		driver.findElement(By.xpath(config.getProperty("ObjSubmit"))).click();
		Thread.sleep(3000);
		//capture alert message
		String ExpectedAlert =driver.switchTo().alert().getText();
		Thread.sleep(3000);
		//click ok to alert
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		String ActualAlert="New Branch with";
		if(ExpectedAlert.toLowerCase().contains(ActualAlert.toLowerCase()))
		{
			Reporter.log(ExpectedAlert,true);
			return true;
		}
		else
		{
			Reporter.log("Unable to create new Branch",true);
			return false;
		}
	}
//method for branch updation
	public static boolean verifyBranchUpdate(String Branch,String Address,String Area,String zip)throws Throwable
	{
		driver.findElement(By.xpath(config.getProperty("ObjEdit"))).click();
		Thread.sleep(3000);
		WebElement element1 =driver.findElement(By.xpath(config.getProperty("ObjBranchName")));
		element1.clear();
		element1.sendKeys(Branch);
		WebElement element2 =driver.findElement(By.xpath(config.getProperty("ObjAddress")));
		element2.clear();
		element2.sendKeys(Address);
		WebElement element3 =driver.findElement(By.xpath(config.getProperty("ObjArea1")));
		element3.clear();
		element3.sendKeys(Area);
		WebElement element4 =driver.findElement(By.xpath(config.getProperty("ObjZip")));
		element4.clear();
		element4.sendKeys(zip);
		driver.findElement(By.xpath(config.getProperty("ObjUpdate"))).click();
		Thread.sleep(4000);
		String ExpectedUpdateAlert =driver.switchTo().alert().getText();
		Thread.sleep(4000);
		driver.switchTo().alert().accept();
		String ActualUpdateAlert ="Branch updated";
		if(ExpectedUpdateAlert.toLowerCase().contains(ActualUpdateAlert.toLowerCase()))
		{
			Reporter.log(ExpectedUpdateAlert,true);
			return true;
		}
		else
		{
			Reporter.log("Branch Update Fail",true);
			return false;
		}
	}
		//method for logout
		public static boolean verifyLogout()throws Throwable
		{
			driver.findElement(By.xpath(config.getProperty("ObjLogout"))).click();
			Thread.sleep(4000);
			if(driver.findElement(By.xpath(config.getProperty("ObjLogin"))).isDisplayed())
			{
				Reporter.log("Logout Success",true);
				return true;
			}
			else
			{
				Reporter.log("Logout Fail",true);
				return false;
			}
		}
public static void add()
{
	int a=5667,b=9876,c;
	c=a+b;
	System.out.println(c);
}
}












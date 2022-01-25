package tests;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import core.Base;
import pageObj.HotelPageObject;
import utilities.DriverUtility;
import utilities.ExcelUtility;

public class DataDrivenTesting extends Base{
	
	// we create object of our Page Object class
	
	
	@BeforeMethod
	public void beforeMethod() {
		initializeDriver();
		logger.info("Hotel Page is opened");
		
	}
	
	@Test(dataProvider ="inputData")
	public void test(String email, String password) {
		HotelPageObject hotel = new HotelPageObject();
		hotel.clickOnSignIn();
		logger.info("user clicked on Sign in");
		hotel.enterEmail(email);
		hotel.enterPassword(password);
		DriverUtility.screenShot();
		logger.info("user entered email " + email);
		logger.info("user entered password " + password);
		hotel.clickOnSignINButton();
		String expectedMessage = "Welcome to your account. Here you can manage all of your personal information and orders.";
		String actualMessage = hotel.successMessage();
		Assert.assertEquals(actualMessage,expectedMessage);
	}
	
	@DataProvider(name ="inputData")
	public Object[][] getTestData(){
		
		Object data[][]= ExcelUtility.getExcelData("info");
		return data;
	}
	
	@AfterMethod
	public void afterMethod() {
		tearDown();
	}

	

}

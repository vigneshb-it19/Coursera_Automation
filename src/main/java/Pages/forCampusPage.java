package Pages;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.base;

public class forCampusPage extends base {

	private By forCampusLink = By.linkText("For Campus");
	private By contactUsICon = By.xpath("//div[@class='css-1jx1bbz']/a");
	private By firstNameField = By.xpath("//input[@placeholder='First Name']");
	private By lastNameField = By.xpath("//input[@placeholder='Last Name']");
	private By jobTitleField = By.xpath("//input[@id='Title']");
	private By primaryField = By.id("Primary_Discipline__c");
	private By workEmailField = By.xpath("//input[@id='Email']");
	private By phoneNumberField = By.xpath("//input[@id='Phone']");
	private By institutionNameField = By.xpath("//input[@id='Company']");
	private By instiutionTypeField = By.id("Institution_Type__c");
	private By countryField = By.id("Country");
	private By bestDescribesField = By.id("What_the_lead_asked_for_on_the_website__c");
	private By submitField = By.xpath("//button[@type='submit']");

	public void forCampus() throws InterruptedException {
		WebElement forCampus = driver.findElement(forCampusLink);
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(forCampusLink));
		forCampus.click();
		TimeUnit.SECONDS.sleep(5);
	}

	public void contactUs() throws InterruptedException {

		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(contactUsICon));
		WebElement contactUs = driver.findElement(contactUsICon);
		contactUs.click();
		TimeUnit.SECONDS.sleep(5);
		WebElement firstName = driver.findElement(firstNameField);
		firstName.sendKeys(sheet.getRow(1).getCell(1).getStringCellValue());
		WebElement lastName = driver.findElement(lastNameField);
		lastName.sendKeys(sheet.getRow(1).getCell(2).getStringCellValue());
		WebElement jobTitle = driver.findElement(jobTitleField);
		jobTitle.sendKeys(sheet.getRow(1).getCell(3).getStringCellValue());

		WebElement primary = driver.findElement(primaryField);
		Select select = new Select(primary);
		select.selectByVisibleText("I am a student");
		TimeUnit.SECONDS.sleep(5);

		WebElement workEmail = driver.findElement(workEmailField);
		workEmail.sendKeys(sheet.getRow(1).getCell(4).getStringCellValue());
		WebElement phoneNumber = driver.findElement(phoneNumberField);
		phoneNumber.sendKeys(sheet.getRow(1).getCell(5).getStringCellValue());
		WebElement institutionName = driver.findElement(institutionNameField);
		institutionName.sendKeys(sheet.getRow(1).getCell(6).getStringCellValue());

		WebElement institutionType = driver.findElement(instiutionTypeField);
		Select select1 = new Select(institutionType);
		select1.selectByVisibleText("Public University");
		TimeUnit.SECONDS.sleep(1);

		WebElement country = driver.findElement(countryField);
		Select select2 = new Select(country);
		select2.selectByVisibleText("United States");
		TimeUnit.SECONDS.sleep(1);

		WebElement bestDescribes = driver.findElement(bestDescribesField);
		Select select3 = new Select(bestDescribes);
		select3.selectByVisibleText("Meet with Coursera Sales Team");
		TimeUnit.SECONDS.sleep(1);
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//div[@class='drift-widget-message-preview-wrapper']//button")));
			WebElement adClose = driver
					.findElement(By.xpath("//div[@class='drift-widget-message-preview-wrapper']//button"));
			adClose.click();
		} catch (Exception e) {
			e.getMessage();
		}

		WebElement submit = driver.findElement(submitField);
		submit.click();

	}

	public void screenshot() throws IOException, InterruptedException {
		TimeUnit.SECONDS.sleep(3);
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File screenshotfile = screenshot.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(screenshotfile, new File(System.getProperty("user.dir") + "\\screenshot\\image.png"));
		

	}
}

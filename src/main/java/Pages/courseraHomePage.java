package Pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.base;

public class courseraHomePage extends base {

	private By s = By.xpath("//input[@placeholder='What do you want to learn?']");

	public void search() throws IOException {
		// driverSetup();
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[@placeholder='What do you want to learn?']")));
		WebElement search = driver.findElement(s);
		search.click();
		search.sendKeys(sheet.getRow(1).getCell(0).getStringCellValue());
		search.sendKeys(Keys.ENTER);
	}

}

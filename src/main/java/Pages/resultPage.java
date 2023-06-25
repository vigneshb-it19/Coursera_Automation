package Pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.base;

public class resultPage extends base {
	// english button - //div[@data-testid='scroll-container']//div[2]/div/div[@data-testid='English-false']/label/span

	// languages list -//div[@data-testid='scroll-container']//div[2]/div/div

	// apply button - //span[text()='Apply']
	
	private By beginIcon     = By.xpath("//span[text()='Beginner']/ancestor::label/span");
	private By showMoreIcon	 = By.xpath("//button[@data-track-component='expand_filter_items_button_language']//*[text()='Show more']");
	private By englishIcon	 = By.xpath("//div[@class='css-k0ld8m']/div[2]//div/div[@data-testid='English-false']");
	private By languagesList = By.xpath("//div[@data-testid='scroll-container']//div[2]/div/div");
	private By applyIcon	 = By.xpath("//span[text()='Apply']");
	private By nameList		 = By.xpath("//div[@class='css-1rj417c']/h2");
	private By ratingList	 = By.xpath("//div[@class='product-reviews css-pn23ng']//p[1]");
	private By durationList  = By.xpath("//div[@class='css-ilhc4l']/div[2]/p");
	private By levels 		 = By.xpath("//label[text()='Level']/parent::div/div/div");
	
	
	public void checkBox() {
		wait = new WebDriverWait(driver, 70);
		wait.until(ExpectedConditions.presenceOfElementLocated(beginIcon));
		WebElement beginner = driver.findElement(beginIcon);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		beginner.click();
	}
	
	public void allLanguages() throws InterruptedException {
		wait = new WebDriverWait(driver, 30);
		WebElement showMoreOption = driver.findElement(showMoreIcon);
		showMoreOption.click();
		
		WebElement englishButton = driver.findElement(englishIcon);
		wait.until(ExpectedConditions.elementToBeClickable(englishIcon));
		englishButton.click();

		List<WebElement> languages = driver.findElements(languagesList);

		System.out.println("********************************");
		System.out.println("Languages : " + languages.size());

		for (int i = 0; i < languages.size(); i++) {
			System.out.println(languages.get(i).getText());
		}
		
		WebElement apply = driver.findElement(applyIcon);
		TimeUnit.SECONDS.sleep(5);
		apply.click();
	}
	
	public void nameResults() throws InterruptedException {
		wait = new WebDriverWait(driver, 30);
		List<WebElement> name = driver.findElements(nameList);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(nameList));
		List<WebElement> rating = driver.findElements(ratingList);
		List<WebElement> Duration = driver.findElements(durationList);
		
		TimeUnit.SECONDS.sleep(5);
		for (int i = 0; i < 2; i++) {
			System.out.println("********************************");
			System.out.println("Name : " + name.get(i).getText());
			System.out.println("Rating : " + rating.get(i).getText());
			System.out.println("Duration : " + Duration.get(i).getText() + "\n");

		}
	}
	
	public void differentLevels() throws InterruptedException {

		 wait = new WebDriverWait(driver, 30);
		 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(levels));
		 List<WebElement> levelOption = driver.findElements(levels);
		 
		System.out.println("********************************");
		System.out.println("The Total levels : " + levelOption.size());

		for (int i = 0; i < levelOption.size(); i++) {
			System.out.println(levelOption.get(i).getText());
		}
		System.out.println("\n");
		navigation();
	}

}

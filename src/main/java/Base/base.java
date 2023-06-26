package Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class base {

	public static WebDriver driver;
	public static WebDriverWait wait;
	public static String url;
	public static Workbook workbook;
	public static Sheet sheet;
	public static Properties properties;

	public void driverSetup() throws IOException {
		
		properties = new Properties();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");
		properties.load(file);
		Scanner scanner = new Scanner(System.in);

		System.out.println("Choose the browser:");
		System.out.println("1. Chrome");
		System.out.println("2. Edge");

		int choice = scanner.nextInt();
		String selectedBrowser;

		if (choice == 1) {
			selectedBrowser = "chrome";
			chromeDriverSetup();
		} else if (choice == 2) {
			selectedBrowser = "edge";
			edgeDriverSetup();
		} else {
			System.out.println("None were Selected");
			return;
		}
//////////////////////////////////////////////////////////////
		DesiredCapabilities capability = DesiredCapabilities.firefox();
        capability.setBrowserName(selectedBrowser);
        capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
        
        URL url;
        try {
            url = new URL("http://localhost:4444/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        }
        
        WebDriver driver;
        driver = new RemoteWebDriver(url, capability);

		driver.get(properties.getProperty("url"));
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		excel();
	}

	private void chromeDriverSetup() {

		System.setProperty("webdriver.chrome.driver",

				System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	private void edgeDriverSetup() {

		System.setProperty("webdriver.edge.driver",

				System.getProperty("user.dir") + "\\drivers\\msedgedriver.exe");
		driver = new EdgeDriver();
	}

	public Sheet excel() throws IOException {
		FileInputStream inputStream = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\InputDatas.xlsx");
		workbook = new XSSFWorkbook(inputStream);
		return sheet = workbook.getSheetAt(0);
	}

	public void navigation() {
		driver.navigate().back();
	}

	public void quit() throws InterruptedException, IOException {
		TimeUnit.SECONDS.sleep(5);
		workbook.close();
		driver.quit();
	}

}

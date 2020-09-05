package com;

import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import au.com.bytecode.opencsv.CSVReader;

public class Shaadi {

	public static void main(String args[]) throws Exception{
		
		
		CSVReader reader = new CSVReader(new FileReader("D:\\Web\\AutomationTask\\Data\\Datasheet.csv"));
		List<String[]> list = reader.readAll();
		Iterator<String[]> iterator = list.iterator();
		
		System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();	
		
		
		while(iterator.hasNext()){
			String[] str = iterator.next();
			
			driver.get(str[0]);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			
			WebElement get_started = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-testid='lets_begin']")));
			get_started.click();
			
			WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='email']")));
			WebElement password = driver.findElement(By.xpath("//input[@name='password1']"));
			WebElement profile_arrow = driver.findElement(By.cssSelector("span.Dropdown-arrow"));			
			
			email.sendKeys(str[1]);
			password.sendKeys(str[2]);
			profile_arrow.click();
			WebElement profile_value = driver.findElement(By.xpath("//div[text()='" +str[3] +"']"));
			profile_value.click();
			
			WebElement next_button = driver.findElement(By.xpath("//button[@data-testid='next_button']"));
			next_button.click();
			
			
			
			WebElement community_arrow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'community_selector')]//span")));
			community_arrow.click();
			
			WebElement community = driver.findElement(By.xpath("//div[text()='" +str[4] +"']"));
			community.click();
			
			WebElement mother_tongue = driver.findElement(By.cssSelector("div.mother_tongue_selector>div.is-selected"));
			mother_tongue.getText().equalsIgnoreCase(str[5]);
			
		}
		reader.close();
		driver.close();		
	}
}

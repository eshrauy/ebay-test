package automationExam.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This class consider static content at the top of Ebay site.
 * @author Usuario
 *
 */
public class EbaySiteTop {
	WebDriver driver;
	
	@FindBy(xpath="//a[@id='gh-eb-Geo-a-default']")
	WebElement languageSelector;
	
	@FindBy(xpath="//span[text()='English']")
	WebElement englishLanguage;
	
	@FindBy(xpath="//li[@class='hl-cat-nav__js-tab']//a[text()='Fashion']")
	WebElement linkFashion;
	
	public void assureEnglishLanguage() {
		languageSelector.click();
		englishLanguage.click();
	}
	
	public void clickFashion() {
		linkFashion.click();
	}
	
	public EbaySiteTop(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
}

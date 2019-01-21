package automationExam.web;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * This class consider content dynamic content of Ebay site.
 * @author Usuario
 *
 */
public class EbaySiteBottom {
	WebDriver driver;
	/**
	 * Xpath selectors for all the elements
	 */
	private String xpathShoes = "//div[@class='pagecontainer__leftnav b-sidenav']//a[text()='Shoes']";
    private String xpathMensShoes = "//div[@class='pagecontainer__leftnav b-sidenav']//a[contains(text(),'Men')]";
	private String xpathBrandFilter = "//input[@class='x-searchable-list__textbox__aspect-Brand']";
    private String xpathBrandPumaChk = "//input[@aria-label='PUMA']";
	private String xpathNewWithBoxChk = "//input[@aria-label='New with box']";
	private String xpathResultsFilter = "//div[@class='srp-controls--selected-value']";
	private String xpathResultNumber = "//h2[@class='srp-controls__count-heading']";
	private String xpathLowestPriceFirst = "//span[text()='Price + Shipping: lowest first']";
	//private String xpathLowestPriceFirst = "//span[text()='Lowest Price + Shipping']";
	private String xpathResultName = "//li[@id='w5-items[0]']//h3[@class='s-item__title']";
	private String xpathResultPrice = "//li[@id='w5-items[0]']//span[@class='s-item__price']";
	private String xpathResultShipping = "//li[@id='w5-items[0]']//span[@class='s-item__shipping s-item__logisticsCost']";
	
    private WebElement getElementByXpath(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}
    
    private String filterStringInfo(String stringToFilter,String start, String end) {
    	String filteredString = stringToFilter.split(start)[1];
    	filteredString = filteredString.split(end)[0];
		return filteredString;
    }
    
	public EbaySiteBottom(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickShoes() {
		getElementByXpath(xpathShoes).click();
		
	}
	
	public void clickMenShoes() {
		getElementByXpath(xpathMensShoes).click();
	}

	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
	}
	
	public void filterBrand(String brandFilterText) {
		WebElement brandFilter = getElementByXpath(xpathBrandFilter);
		brandFilter.sendKeys(brandFilterText);;
	}
	
	public void checkPumaBrand() {
		WebElement pumaBrandChk = getElementByXpath(xpathBrandPumaChk);
		pumaBrandChk.click();
	}
	
	public void checkNewWithBox() {
		WebElement newWithBoxChk = getElementByXpath(xpathNewWithBoxChk);
		newWithBoxChk.click();
	}
	
	public String getResultsNumber() {
		WebElement resultsNumberElement = getElementByXpath(xpathResultNumber);
		return filterStringInfo(resultsNumberElement.getText(),"of "," Results");
	}
	
	public void clickResultsFilter() {
		getElementByXpath(xpathResultsFilter).click();
	}
	
	public void selectLowestPriceFirst() {
		getElementByXpath(xpathLowestPriceFirst).click();
	}
	
	public String getResultName(int index) {
		String xpathResultNameEdited=xpathResultName.replace("0", String.valueOf(index));
		return driver.findElement(By.xpath(xpathResultNameEdited)).getText();
	}
	
	public double getResultPrice(int index) {
		String xpathResultPriceEdited=xpathResultPrice.replace("0", String.valueOf(index));
		String stringResultPrice = driver.findElement(By.xpath(xpathResultPriceEdited)).getText();
		double resultPrice = Double.valueOf(stringResultPrice.substring(5));
		return resultPrice;
	}
	
	public double getResultShipping(int index) {
		String xpathResultShippingEdited=xpathResultShipping.replace("0", String.valueOf(index));
		String stringResultShipping = driver.findElement(By.xpath(xpathResultShippingEdited)).getText();
		stringResultShipping = stringResultShipping.substring(5).split(" shipping")[0];
		double resultShipping;
		
		if(stringResultShipping.equals("free")) {
			resultShipping = Double.valueOf(0);
		}else {
			resultShipping = Double.valueOf(stringResultShipping); 
		}
		return resultShipping;
	}
}

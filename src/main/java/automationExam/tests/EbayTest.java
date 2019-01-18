package automationExam.tests;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.testng.Reporter;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import automationExam.utils.EbayResultObject;
import automationExam.utils.EbayTestHelper;
import automationExam.web.EbaySiteBottom;
import automationExam.web.EbaySiteTop;
import automationExam.tests.EbayAssert;

/**
 * Ebay tests for automation exam
 *
 */
public class EbayTest 
{
	private WebDriver driver;
	private String baseUrl;

	@BeforeTest
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "https://www.ebay.com/";
		
		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void pumaShoesFiltering() {
		driver.get(baseUrl);
		EbaySiteTop ebaySearch = new EbaySiteTop(driver);
		ebaySearch.assureEnglishLanguage();
		ebaySearch.clickFashion();

		EbaySiteBottom ebayDynamicInfo = new EbaySiteBottom(driver);
		ebayDynamicInfo.clickShoes();
		ebayDynamicInfo.clickMenShoes();
		ebayDynamicInfo.scrollDown();
		ebayDynamicInfo.filterBrand("puma");
		ebayDynamicInfo.checkPumaBrand();
		ebayDynamicInfo.scrollDown();
		ebayDynamicInfo.checkNewWithBox();
		
        Reporter.log("Number of results obtained: " + ebayDynamicInfo.getResultsNumber(), true);
        ebayDynamicInfo.scrollDown();
        
        ebayDynamicInfo.clickResultsFilter();
        ebayDynamicInfo.selectLowestPriceFirst();
        
        ebayDynamicInfo.scrollDown();
        
        ArrayList<EbayResultObject> resultsArrayList= EbayTestHelper.getResultsArrayList(ebayDynamicInfo);
        EbayAssert.evaluateAscOrder(resultsArrayList);
        
        EbayTestHelper.printResultsArrayList(resultsArrayList, "ASC by total price");
        EbayTestHelper.printResultsArrayList(EbayTestHelper.orderResultsByNameAsc(resultsArrayList), "ASC by name");
        EbayTestHelper.printResultsArrayList(EbayTestHelper.orderResultsByTotalPriceDes(resultsArrayList), "DSC by total price");
        
        EbayAssert.getTestResults();
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
		Reporter.getCurrentTestResult();
		EbayTestHelper.SendFileEmail();
	}
}

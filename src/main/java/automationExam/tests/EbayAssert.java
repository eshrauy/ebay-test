package automationExam.tests;

import java.util.ArrayList;

import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import automationExam.utils.EbayResultObject;

public class EbayAssert {
	protected static SoftAssert sa = new SoftAssert();
	
	public static void evaluateAscOrder(ArrayList<EbayResultObject> resultObjectsArrayList) {
		double lastTotalPrice = Double.valueOf(0);
		
		for(EbayResultObject aResultObject : resultObjectsArrayList) {
			double totalPrice = aResultObject.getPrice() + aResultObject.getShipping();
			sa.assertTrue(lastTotalPrice <= totalPrice, "Ascendant order comparison FAILED! Value: " + lastTotalPrice + " > " + totalPrice);
			lastTotalPrice = totalPrice;
		}
	}
	
     /**
     * Obtain the results of the assertions
     *
     * @param sa
     */
    public static void getTestResults() {
        try {sa.assertAll();} catch (AssertionError ae) {Reporter.log("[ERROR]: [TEST FAILED] - " + ae.getMessage(), true);}
        sa.assertAll();
    }

}

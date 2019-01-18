package automationExam.utils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Properties;

import org.testng.Reporter;

import automationExam.web.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class EbayTestHelper {
	public static ArrayList<EbayResultObject> getResultsArrayList(EbaySiteBottom pageResults) {
		ArrayList<EbayResultObject> resultsArrayList = new ArrayList<>();
		
		for(int i=0; i<5; i++) {
			resultsArrayList.add(new EbayResultObject(pageResults.getResultName(i), pageResults.getResultPrice(i), pageResults.getResultShipping(i)));
		}
		return resultsArrayList;
	} 
	
	public static void printResultsArrayList(ArrayList<EbayResultObject> resultObjectsArrayList, String orderType) {
		Reporter.log("\n****************************************************************", true);
        Reporter.log("          Results obtained - Order: " + orderType, true);
        Reporter.log("****************************************************************", true);
        for(EbayResultObject aResultObject : resultObjectsArrayList) {
        	Reporter.log("Name: " + aResultObject.getName(), true);
        	Reporter.log("Price: " + aResultObject.getPrice(), true);
        	Reporter.log("Shipping: " + aResultObject.getShipping(), true);
        	Reporter.log("****************************************************************", true);
        }
		Reporter.log("\n",true);
	}
	
	public static ArrayList<EbayResultObject> orderResultsByNameAsc(ArrayList<EbayResultObject> resultObjectsArrayList) {
		resultObjectsArrayList.sort(Comparator.comparing(EbayResultObject::getName));
		return resultObjectsArrayList;
	}
	
	public static ArrayList<EbayResultObject> orderResultsByTotalPriceDes(ArrayList<EbayResultObject> resultObjectsArrayList) {
		resultObjectsArrayList.sort(Comparator.comparing(EbayResultObject::getTotalPrice).reversed());
		return resultObjectsArrayList;
	}
	
	
	public static void SendFileEmail() {
	    final String username ="automationexam2019";
	    final String password = "autoMagic2019";

	    Properties props = new Properties();
	    props.put("mail.smtp.auth", true);
	    props.put("mail.smtp.starttls.enable", true);
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");

	    Session session = Session.getInstance(props,
	            new javax.mail.Authenticator() {
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(username, password);
	                }
	            });

	    try {

	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress("automationexam2019@gmail.com"));
	        message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse("automationexam2019@gmail.com"));
	        message.setSubject("Result report of automation exam");
	        message.setText("PFA");

	        MimeBodyPart messageBodyPart = new MimeBodyPart();

	        Multipart multipart = new MimeMultipart();

	        messageBodyPart = new MimeBodyPart();
	        String file = "./test-output/emailable-report.html";
	        String fileName = "emailable-report.html";
	        DataSource source = new FileDataSource(file);
	        messageBodyPart.setDataHandler(new DataHandler(source));
	        messageBodyPart.setFileName(fileName);
	        multipart.addBodyPart(messageBodyPart);

	        message.setContent(multipart);

	        System.out.println(">>> Sending results report email >>>");

	        Transport.send(message);

	        System.out.println(">>> Done <<<");

	    } catch (MessagingException e) {
	        e.printStackTrace();
	    }
	}
}

package Utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.io.IOException;
import java.util.Properties;

public class RetryAnalyzer implements IRetryAnalyzer {

    FileProcessing processing = new FileProcessing();
    int counter = 0;

    public RetryAnalyzer() throws IOException {
    }
    Properties prop = processing.readPropertiesFile("TestData.properties");
    int retryLimit = Integer.parseInt(prop.getProperty("retryLimit"));

 /*
 * (non-Javadoc)
 * @see org.testng.IRetryAnalyzer#retry(org.testng.ITestResult)
 * 
 * This method decides how many times a test needs to be rerun.
 * TestNg will call this method every time a test fails. So we 
 * can put some code in here to decide when to rerun the test.
 * 
 * Note: This method will return true if any failed tests needs to be retried
 * and false if not.
 *
 */
 
 @Override
 public boolean retry(ITestResult result) {
 
     if(counter < retryLimit)
     {
         System.out.println("Retry invoked for: " + result.getName() + " | Retry old count: " + counter);
         counter++;
         System.out.println("Retry count++: " + counter);
         return true;
     }
     return false;
 }

}
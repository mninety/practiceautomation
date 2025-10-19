package WebTests;

import Init.BaseRunner;
import Utils.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Utils.TestMethodListener.class)
public class WebTestScenarios extends BaseRunner {

    public WebTestScenarios() throws Exception {
    }

    @Test(description = "FirstWebTest", enabled = true, retryAnalyzer = RetryAnalyzer.class)
    public void FirstWebTest() throws InterruptedException {
        System.out.println("Web Driver: "+wdriver);
        wdriver.get(webURL);
        Thread.sleep(Long.parseLong(shortSleep));
        Assert.assertNotEquals(wdriver,null,"Test Failed!!!");

    }
}

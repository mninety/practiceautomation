package APITests;

import APIs.listGET;
import Init.BaseRunner;
import Utils.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners(Utils.TestMethodListener.class)
public class APITestScenarios extends BaseRunner {
    public APITestScenarios() throws Exception {
    }

    @Test(description = "FirstAPITest", enabled = true, retryAnalyzer = RetryAnalyzer.class)
    public void FirstAPITest() throws InterruptedException, IOException {
        listGET listapi = new listGET();
        listapi.listAPI();
        System.out.println("API Call: "+listapi.getId(2));
        Assert.assertEquals(listapi.getId(2),"3","Test Failed!!!");
    }

}

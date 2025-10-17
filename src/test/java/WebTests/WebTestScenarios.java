package WebTests;

import Init.BaseRunner;
import org.testng.annotations.Test;

public class WebTestScenarios extends BaseRunner {

    public WebTestScenarios() throws Exception {
    }

    @Test(description = "FirstWebTest")
    public void FirstWebTest() throws InterruptedException {
        System.out.println("Web Driver: "+wdriver);
        wdriver.get(webURL);
        Thread.sleep(Long.parseLong(shortSleep));

    }
}

package APITests;

import APIs.listGET;
import Init.BaseRunner;
import org.testng.annotations.Test;

import java.io.IOException;

public class APITestScenarios extends BaseRunner {
    public APITestScenarios() throws Exception {
    }

    @Test(description = "FirstAPITest")
    public void FirstAPITest() throws InterruptedException, IOException {
        listGET listapi = new listGET();
        listapi.listAPI();
        System.out.println("API Call: "+listapi.getId(2));

    }

}

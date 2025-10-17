package AppTests;

import Init.BaseRunner;
import org.testng.annotations.Test;

public class AppTestScenarios extends BaseRunner {

    public AppTestScenarios() throws Exception {
    }

    @Test(description = "FirstTest")
    public void FirstTest()
    {
        System.out.println("Android Driver: "+adriver);

    }
}

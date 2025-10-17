package AppTests;

import Init.BaseRunner;
import com.google.common.base.Verify;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AppTestScenarios extends BaseRunner {

    public AppTestScenarios() throws Exception {
    }

    @Test(description = "FirstTest", enabled = false)
    public void FirstTest()
    {
        System.out.println("Android Driver: "+adriver);
        Assert.assertNotEquals(adriver,null,"Test Failed!!!");

    }
}

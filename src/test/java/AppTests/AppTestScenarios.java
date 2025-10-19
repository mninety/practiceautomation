package AppTests;

import Init.BaseRunner;
import Utils.RetryAnalyzer;
import com.google.common.base.Verify;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Utils.TestMethodListener.class)
public class AppTestScenarios extends BaseRunner {

    public AppTestScenarios() throws Exception {
    }

    @Test(description = "FirstTestforAndroid", enabled = true, retryAnalyzer = RetryAnalyzer.class)
    public void FirstTestforAndroid()
    {
        System.out.println("Android Driver: "+adriver);
        Assert.assertNotEquals(adriver,null,"Test Failed!!!");

    }

    @Test(description = "FirstTestforiOS", enabled = true, retryAnalyzer = RetryAnalyzer.class)
    public void FirstTestforiOS()
    {
        System.out.println("iOS Driver: "+idriver);
        Assert.assertNotEquals(idriver,null,"Test Failed!!!");

    }

}

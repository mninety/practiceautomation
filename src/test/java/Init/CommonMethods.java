package Init;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;

public class CommonMethods extends BaseRunner {

    long veryShortSleep=1000;
    int wait=10;

    public CommonMethods() throws Exception {
    }

    public boolean isElementPresent(AppiumDriver driver, WebElement locator, int timeoutInSeconds) throws InterruptedException {
        Thread.sleep(veryShortSleep);
        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.visibilityOf(locator));
            //System.out.println("Element Found!!! ");
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


    public String performGetTextOperation(AppiumDriver driver, WebElement element, int retry) throws InterruptedException {
        Thread.sleep(veryShortSleep);
        String text="";
        for(int i=0;i<retry;i++) {
            if (isElementPresent(driver, element, wait)) {
                text = element.getText();
                break;
            } else {
                System.out.println("Element not found from function performGetTextOperation!!! " + element);
            }
        }
        return text;
    }

    public void performsendKeysOperation(AppiumDriver driver, WebElement element, String sendData, int retry) throws InterruptedException {
        Thread.sleep(veryShortSleep);
        for(int i=0;i<retry;i++) {
            if (isElementPresent(driver, element, wait)) {
                element.sendKeys(sendData);
                break;
            } else {
                System.out.println("Element not found from function performsendKeysOperation!!! " + element);
            }
        }
    }

    public void performClickOperation(AppiumDriver driver, WebElement element, int retry) throws InterruptedException {
        Thread.sleep(veryShortSleep);
        for(int i=0;i<retry;i++) {
            if (isElementPresent(driver, element, wait)) {
                System.out.println("Element found from function performClickOperation!!! " + element);
                element.click();
                break;
            }
            else {
                System.out.println("Element not found from function performClickOperation!!! " + element);
                if(i<retry)
                {
                    System.out.println("Retrying element operation for " + element);
                }
                if(i==(retry-1))
                {
                    throw new RuntimeException("Force stopping this test due to invalid condition");
                }
            }
        }
    }

    public void ScrollDown(AppiumDriver driver, double speed) throws InterruptedException {

        Dimension size= driver.manage().window().getSize();
        int startX= size.getWidth()/2;
        int startY= size.getHeight()/2;

        int endX=startX;
        int endY= (int) (size.getHeight()*speed);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger,1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(200)))
                .addAction(finger.createPointerMove(Duration.ofMillis(200),PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(sequence));
        Thread.sleep(Long.parseLong(shortSleep));

    }

    public void reopeniOSApp(AppiumDriver driver, String appPackage, int action) throws InterruptedException {
        if(action==0)
        {
            ((IOSDriver) driver).terminateApp(appPackage);
            Thread.sleep(Long.valueOf(shortSleep));
        }
        else if(action==1){
            ((IOSDriver) driver).activateApp(appPackage);
            Thread.sleep(Long.valueOf(shortSleep));
        }
        else {
            ((IOSDriver) driver).terminateApp(appPackage);
            Thread.sleep(Long.valueOf(shortSleep));
            ((IOSDriver) driver).activateApp(appPackage);
            Thread.sleep(Long.valueOf(shortSleep));
        }
    }

    public void reopenAndroidApp(AppiumDriver driver, String appPackage, int action) throws InterruptedException {
        if(action==0)
        {
            ((AndroidDriver) driver).terminateApp(appPackage);
            Thread.sleep(Long.valueOf(shortSleep));
        }
        else if(action==1)
        {
            ((AndroidDriver) driver).activateApp(appPackage);
            Thread.sleep(Long.valueOf(shortSleep));
        }
        else {
            ((AndroidDriver) driver).terminateApp(appPackage);
            Thread.sleep(Long.valueOf(shortSleep));
            ((AndroidDriver) driver).activateApp(appPackage);
            Thread.sleep(Long.valueOf(shortSleep));
        }

    }


}

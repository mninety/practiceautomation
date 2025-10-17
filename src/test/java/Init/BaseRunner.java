package Init;

import Utils.FileProcessing;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

@Listeners(Utils.TestMethodListener.class)
public class BaseRunner {

    FileProcessing processing = new FileProcessing();
    //public int port=findAvailablePort();
    public  int appiumPort=4723;
    public  WebDriver wdriver;
    public  String parentWindow;
    public  AppiumDriver adriver;
    public  AppiumDriver addriver;
    public  AppiumDriver idriver;
    public  AppiumDriver iddriver;

    public String webURL;
    public String appiumURL;
    public String apiURL;

    public String browserName;
    public String deviceName;
    public String driverDeviceName;
    public String iOSDeviceName;
    public String iOSDriverDeviceName;
    public String customerDeviceID;
    public String iosBundleID;
    public String iosDriverBundleID;
    public String customerDeviceName;
    public String customerPlatformVersion;
    public String customerappPackage;
    public String customerappActivity;

    public String driverPackage;
    public String driverActivity;

    public String chromePackage;
    public String chromeActivity;

    public String androidAutomation;
    public String iosAutomation;
    public String ssforFailedTC;
    public String  webAutomation;

    public String otp;
    public  String env;


    public String driver1;
    public String rider1;
    public String iOSdriver1;
    public String iOSrider1;

    public String waitingTime;
    public int retryLimit;
    public String shortSleep;
    public String midSleep;
    public String deepSleep;

    public BaseRunner() throws Exception {
        variableInitialization();

    }


    public void variableInitialization() throws IOException {
        Properties prop = processing.readPropertiesFile("TestData.properties");
        webURL = prop.getProperty("webURL");
        appiumURL = prop.getProperty("appiumURL");
        apiURL = prop.getProperty("apiURL");
        retryLimit = Integer.parseInt(prop.getProperty("retryLimit"));

        browserName=System.getProperty("browserName",prop.getProperty("browserName"));
        deviceName=System.getProperty("deviceName",prop.getProperty("deviceName"));
        driverDeviceName=System.getProperty("driverDeviceName",prop.getProperty("driverDeviceName"));
        iOSDeviceName=System.getProperty("iOSDeviceName",prop.getProperty("iOSDeviceName"));
        iOSDriverDeviceName=System.getProperty("iOSDriverDeviceName",prop.getProperty("iOSDriverDeviceName"));
        customerappPackage = prop.getProperty("customerappPackage");
        customerappActivity=System.getProperty("customerappActivity",prop.getProperty("customerappActivity"));

        driverPackage = prop.getProperty("driverPackage");
        driverActivity=System.getProperty("driverActivity",prop.getProperty("driverActivity"));

        chromePackage = prop.getProperty("chromePackage");
        chromeActivity = prop.getProperty("chromeActivity");

        androidAutomation = prop.getProperty("androidAutomation");
        iosAutomation = prop.getProperty("iosAutomation");
        ssforFailedTC = prop.getProperty("ssforFailedTC");
        webAutomation = prop.getProperty("webAutomation");

        otp = prop.getProperty("otp");
        env=System.getProperty("env",prop.getProperty("env"));
        waitingTime = prop.getProperty("waitingTime");
        shortSleep = prop.getProperty("shortSleep");
        midSleep = prop.getProperty("midSleep");
        deepSleep = prop.getProperty("deepSleep");
    }

    public void appLauncherInitiator() throws Exception {
        if(iosAutomation.equals("1"))
        {

            if(iOSDeviceName != null && !iOSDeviceName.isEmpty())
            {
                System.out.println("iOSDeviceName: "+iOSDeviceName);
                getDeviceCapabilities(iOSDeviceName);
                if(idriver == null) {
                    System.out.println("iOS Rider: "+idriver);
                    idriver = iOSAppLauncher(idriver, customerDeviceName, customerDeviceID, customerPlatformVersion, iosBundleID);
                    System.out.println("iOS Rider: "+idriver);
                }

            }

            if(iOSDriverDeviceName != null && !iOSDriverDeviceName.isEmpty())
            {
                System.out.println("iOSDriverDeviceName: "+iOSDriverDeviceName);
                getDeviceCapabilities(iOSDriverDeviceName);
                if(iddriver == null) {
                    System.out.println("iOS Driver: "+iddriver);
                    iddriver = iOSAppLauncher(iddriver, customerDeviceName, customerDeviceID, customerPlatformVersion, iosDriverBundleID);
                    System.out.println("iOS Driver: "+iddriver);
                }

            }


        }

        if(androidAutomation.equals("1"))
        {

            if(deviceName != null && !deviceName.isEmpty())
            {
                System.out.println("deviceName: "+deviceName);
                getDeviceCapabilities(deviceName);
                if(adriver == null) {
                    System.out.println("Android Rider: "+adriver);
                    adriver = androidAppLauncher(adriver, customerDeviceName, customerDeviceID, customerPlatformVersion, customerappPackage, customerappActivity);
                    System.out.println("Android Rider: "+adriver);
                }
            }

            if(driverDeviceName != null && !driverDeviceName.isEmpty())
            {
                System.out.println("driverDeviceName: "+driverDeviceName);
                getDeviceCapabilities(driverDeviceName);
                if(addriver == null) {
                    //System.out.println("Android Driver: "+addriver);
                    addriver = androidAppLauncher(addriver, customerDeviceName, customerDeviceID, customerPlatformVersion, driverPackage, driverActivity);
                    //System.out.println("Android Driver: "+addriver);
                }
            }


        }

    }

    public void ExecuteCommand(String command) throws IOException {
        Process P;
        P=Runtime.getRuntime().exec(command, null, null);
        try {
            P.waitFor();
            //Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String result = getOutputFromCommand(P);
        System.out.println("Output: "+result);
    }

    public String getOutputFromCommand(Process process) throws IOException {
        String output2 = "";
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            output2 = output2.concat(line);
        }
        return output2;
    }

    public int findAvailablePort() throws IOException {
        int retries = 10;
        while (retries > 0) {
            try (ServerSocket socket = new ServerSocket(0)) {
                return socket.getLocalPort();
            } catch (IOException e) {
                retries--;
            }
        }
        throw new IOException("Could not find available port after 10 attempts");
    }

    public void getDeviceCapabilities(String deviceName) throws IOException {
        if(deviceName.equals("Emulator.properties"))
        {
            Properties prop = processing.readPropertiesFile("DeviceProperties/"+deviceName);
            customerDeviceID = prop.getProperty("customerDeviceID");
            customerDeviceName = prop.getProperty("customerDeviceName");
            customerPlatformVersion = prop.getProperty("customerPlatformVersion");

            rider1 = prop.getProperty("rider1");
            driver1 = prop.getProperty("driver1");

        }
        else if(deviceName.equals("OppoA38.properties"))
        {
            Properties prop = processing.readPropertiesFile("DeviceProperties/"+deviceName);
            customerDeviceID = prop.getProperty("customerDeviceID");
            customerDeviceName = prop.getProperty("customerDeviceName");
            customerPlatformVersion = prop.getProperty("customerPlatformVersion");

            rider1 = prop.getProperty("rider1");
            driver1 = prop.getProperty("driver1");

        }
        else if(deviceName.equals("Redmi7.properties"))
        {
            Properties prop = processing.readPropertiesFile("DeviceProperties/"+deviceName);
            customerDeviceID = prop.getProperty("customerDeviceID");
            customerDeviceName = prop.getProperty("customerDeviceName");
            customerPlatformVersion = prop.getProperty("customerPlatformVersion");

            rider1 = prop.getProperty("rider1");
            driver1 = prop.getProperty("driver1");
            System.out.println("Driver1: "+driver1);
        }
        else if(deviceName.equals("VivoV29.properties"))
        {
            Properties prop = processing.readPropertiesFile("DeviceProperties/"+deviceName);
            customerDeviceID = prop.getProperty("customerDeviceID");
            customerDeviceName = prop.getProperty("customerDeviceName");
            customerPlatformVersion = prop.getProperty("customerPlatformVersion");

            rider1 = prop.getProperty("rider1");
            driver1 = prop.getProperty("driver1");
        }
        else if(deviceName.equals("VivoV30.properties"))
        {
            Properties prop = processing.readPropertiesFile("DeviceProperties/"+deviceName);
            customerDeviceID = prop.getProperty("customerDeviceID");
            customerDeviceName = prop.getProperty("customerDeviceName");
            customerPlatformVersion = prop.getProperty("customerPlatformVersion");

            rider1 = prop.getProperty("rider1");
            driver1 = prop.getProperty("driver1");

        }
        else if(deviceName.equals("iPhone13.properties"))
        {
            Properties prop = processing.readPropertiesFile("DeviceProperties/"+deviceName);
            customerDeviceID = prop.getProperty("customerDeviceID");
            customerDeviceName = prop.getProperty("customerDeviceName");
            customerPlatformVersion = prop.getProperty("customerPlatformVersion");
            iosBundleID = prop.getProperty("iosBundleID");
            iosDriverBundleID=prop.getProperty("iosDriverBundleID");

            iOSrider1 = prop.getProperty("iOSrider1");
            iOSdriver1 = prop.getProperty("iOSdriver1");

        }
        else if(deviceName.equals("iPhoneEmulator.properties"))
        {
            Properties prop = processing.readPropertiesFile("DeviceProperties/"+deviceName);
            customerDeviceID = prop.getProperty("customerDeviceID");
            customerDeviceName = prop.getProperty("customerDeviceName");
            customerPlatformVersion = prop.getProperty("customerPlatformVersion");
            iosBundleID = prop.getProperty("iosBundleID");
            iosDriverBundleID=prop.getProperty("iosDriverBundleID");

            iOSrider1 = prop.getProperty("iOSrider1");
            iOSdriver1 = prop.getProperty("iOSdriver1");

        }

    }

    public AppiumDriver androidAppLauncher(AppiumDriver driver, String deviceName, String deviceID, String platformVersion, String appPackage, String appActivity) throws Exception {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setDeviceName(deviceName);
        options.setUdid(deviceID);
        options.setAutomationName("UiAutomator2");
        options.setCapability("systemPort", findAvailablePort());        // Unique systemPort per device
        options.setPlatformVersion(platformVersion);
        options.setAppPackage(appPackage);
        options.setAppActivity(appActivity);
        options.setCapability("noReset", true); // Don't reset app state between sessions
        options.setCapability("forceAppLaunch", true);
        options.setCapability("skipUnlock", true);
        options.setCapability("autoAcceptAlerts", true);
        options.setCapability("autoDismissAlerts", true);
        options.setCapability("autoGrantPermissions", true);
        options.setCapability("enableMultiWindows", true);
        options.setCapability("showAdbLog", true);
        driver = new AndroidDriver(new URL("http://localhost:"+appiumPort+"/wd/hub"), options);
        Thread.sleep(Long.parseLong(midSleep));
        return driver;
    }

    public AppiumDriver iOSAppLauncher(AppiumDriver driver, String deviceName, String deviceID, String platformVersion, String appPackage) throws Exception {

        UiAutomator2Options options = new UiAutomator2Options();

        // Set desired capabilities
        options.setAutomationName("XCUITest");
        options.setPlatformName("iOS");
        options.setDeviceName(deviceName);
        if(deviceID!=null) {
            System.out.println("Driver1 UDID:" +deviceID);
            options.setUdid(deviceID);
        }
        //options.setApp("/path/to/your/app.apk");
        options.setPlatformVersion(platformVersion);
        options.setCapability("wdaLocalPort", findAvailablePort()); // Must be unique per device
        options.setCapability("appium:appium:wdaLaunchTimeout", 120000);
        options.setCapability("noReset", true); // Don't reset app state between sessions
        //options.setCapability("forceAppLaunch", true);
        options.setCapability("skipUnlock", true);
        options.setCapability("autoAcceptAlerts", true);
        options.setCapability("autoDismissAlerts", true);
        options.setCapability("autoGrantPermissions", true);
        options.setCapability("enableMultiWindows", true);
        options.setCapability("showAdbLog", true);
        options.setCapability("bundleId", appPackage);
        options.setCapability("xcodeOrgId", "HXDF4G78Q9");
        options.setCapability("xcodeSigningId", "iPhone Developer");
        //capabilities.setCapability("appium:app", prop.getProperty("appPath"));
        driver = new IOSDriver(new URL("http://localhost:"+appiumPort+"/wd/hub"), options);
        Thread.sleep(Long.parseLong(midSleep));
        return driver;
    }


    public void setUpWebDriver() throws Exception {

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            // Disable password manager prompts
            options.setExperimentalOption("prefs", Map.of(
                    "credentials_enable_service", false,
                    "profile.password_manager_enabled", false
            ));
            String version = "latest";
            options.setBrowserVersion(version);
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            wdriver = new ChromeDriver(options);
            //DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            //capabilities.setCapability("chrome.arguments", "-screenwidth 2048 -screenheight 1024");
            wdriver.manage().window().maximize();
            //wdriver.manage().window().setSize(new Dimension(300, 600));
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            wdriver = new FirefoxDriver();
            wdriver.manage().window().maximize();
        } else if (browserName.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().setup();
            wdriver = new InternetExplorerDriver();
            wdriver.manage().window().maximize();
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            wdriver = new EdgeDriver();
            wdriver.manage().window().maximize();
        } else if (browserName.equalsIgnoreCase("safari")) {
            WebDriverManager.safaridriver().setup();
            //SafariOptions options = new SafariOptions();
            //options.setUseTechnologyPreview(true);
            wdriver = new SafariDriver();
            wdriver.manage().window().maximize();
        } else if (browserName.equalsIgnoreCase("headless")) {
            ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--disable-notifications");
            // Disable password manager prompts
            chromeOptions.setExperimentalOption("prefs", Map.of(
                    "credentials_enable_service", false,
                    "profile.password_manager_enabled", false
            ));
            wdriver = new ChromeDriver(chromeOptions);
            wdriver.manage().window().maximize();

        }
        parentWindow=wdriver.getWindowHandle();
        System.out.println("Parent Window: "+parentWindow);

    }



    @BeforeSuite
    public void beforeSuit() throws Exception {

        //secureVariableInitialization();
        variableInitialization();
        //port=findAvailablePort();
        if(androidAutomation.equals("1") || iosAutomation.equals("1"))
        {

            appLauncherInitiator();
        }
        if(webAutomation.equals("1"))
        {
            setUpWebDriver();
            wdriver.get(webURL);
            Thread.sleep(Long.valueOf(midSleep));

        }



    }


    @AfterMethod
    public void takingScreenshot(ITestResult result) throws IOException {
        if(ssforFailedTC.equals("1")) {

            if (result.getStatus() == ITestResult.FAILURE) {
                if (webAutomation.equals("1")) {
                    TakesScreenshot wts = (TakesScreenshot) wdriver;
                    File wfile = wts.getScreenshotAs(OutputType.FILE);
                    FileHandler.copy(wfile, new File("Images/FailedTestCaseSS/Web/" + result.getTestClass() + "-" + result.getName() + ".png"));
                }
                if (androidAutomation.equals("1")) {
                    System.out.println("Android Test Failed: "+result.getName());
                    if(adriver!=null) {
                        TakesScreenshot ats = (TakesScreenshot) adriver;
                        File afile = ats.getScreenshotAs(OutputType.FILE);
                        FileHandler.copy(afile, new File("Images/FailedTestCaseSS/Android/Rider/" + result.getName() + ".png"));
                    }
                    if(addriver!=null)
                    {
                        TakesScreenshot atsd = (TakesScreenshot) addriver;
                        File afiled = atsd.getScreenshotAs(OutputType.FILE);
                        FileHandler.copy(afiled, new File("Images/FailedTestCaseSS/Android/Driver/"+ result.getName() + ".png"));
                    }


                }
                if (iosAutomation.equals("1")) {
                    System.out.println("iOS Test Failed: "+result.getName());
                    if(idriver!=null) {
                        TakesScreenshot its = (TakesScreenshot) idriver;
                        File ifile = its.getScreenshotAs(OutputType.FILE);
                        FileHandler.copy(ifile, new File("Images/FailedTestCaseSS/IOS/Rider/"+ result.getName() + ".png"));
                    }
                    if(iddriver!=null){
                        TakesScreenshot itsd = (TakesScreenshot) iddriver;
                        File ifiled = itsd.getScreenshotAs(OutputType.FILE);
                        FileHandler.copy(ifiled, new File("Images/FailedTestCaseSS/IOS/Driver/"+ result.getName() + ".png"));
                    }


                }
            }
        }

    }

    @AfterSuite
    public void afterSuit() throws Exception {

        if(androidAutomation.equals("1") || iosAutomation.equals("1")) {
                //CycleFlowAndtoiOSTests andtoios= new CycleFlowAndtoiOSTests();

                if (adriver != null) {

                    //andtoios.reopenAndroidApp(adriver, customerappPackage,0);
                    System.out.println("Adnroid Rider: "+adriver);
                    adriver.quit();
                }
                if (addriver != null) {
                    //andtoios.reopenAndroidApp(addriver, driverPackage,0);
                    System.out.println("Adnroid Driver: "+addriver);
                    addriver.quit();
                    System.out.println("Adnroid Driver: "+addriver);
                }
                if (idriver != null) {
                    //andtoios.reopeniOSApp(idriver, iosBundleID, 0);
                    System.out.println("iOS Rider: "+idriver);
                    idriver.quit();
                }
                if (iddriver != null) {
                    //andtoios.reopeniOSApp(iddriver, iosDriverBundleID, 0);
                    System.out.println("iOS Driver: "+iddriver);
                    iddriver.quit();
                }

        }
        if(webAutomation.equals("1")) {
            Thread.sleep(Long.valueOf(shortSleep));
            wdriver.quit();

        }
    }
}

package com.amazon;


import com.cucumber.listener.Reporter;
import com.amazon.base_Page.Base_Page;
import com.amazon.property_Reader.Property_Reader;
import com.amazon.utility.Utility;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.io.IOException;

public class Hooks extends Base_Page {
    // junit @before annotation
    @Before
    // open thr browser
    public void openBrowser() {
        //getting browser from property file
        selectBrowser(Property_Reader.getInstance().getProperty("browser"));
        Reporter.assignAuthor("Vishal Tailor");
    }

    // junit after annotation
    @After
    // closed the browser and take a failed test screen shots
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenShotPath = Utility.getScreenshot(driver, scenario.getName().replace(" ", "_"));
            try {
                Reporter.addScreenCaptureFromPath(screenShotPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        closeBrowser();
    }
}

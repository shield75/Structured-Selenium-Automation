package org.example.tests.vendorportal;

import org.example.tests.AbstractTest;
import org.example.tests.vendorportal.model.VendorPortalTestData;
import org.example.pages.vendorsportal.DashboardPage;
import org.example.pages.vendorsportal.LoginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.Config;
import util.Constants;
import util.JsonUtil;

public class VendorPortalTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(VendorPortalTest.class);
    
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath) {
        log.info("Initializing test with data from: {}", testDataPath);
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }

    @Test(description = "Verify vendor login functionality")
    public void loginTest() {
        log.info("Starting vendor login test");
        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt(), "Login page is not displayed");
        loginPage.login(testData.username(), testData.password());
        log.info("Login test completed successfully");
    }

    @Test(dependsOnMethods = "loginTest", description = "Verify dashboard metrics and search functionality")
    public void dashboardTest() {
        log.info("Starting dashboard verification test");
        Assert.assertTrue(dashboardPage.isAt(), "Dashboard page is not displayed");

        // finance metrics verification
        log.info("Verifying financial metrics");
        Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.monthlyEarning(), 
            "Monthly earning mismatch");
        Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.annualEarning(), 
            "Annual earning mismatch");
        Assert.assertEquals(dashboardPage.getProfitMargin(), testData.profitMargin(), 
            "Profit margin mismatch");
        Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.availableInventory(), 
            "Available inventory mismatch");

        // order history search verification
        log.info("Testing order history search with keyword: {}", testData.searchKeyword());
        dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultsCount(), testData.searchResultsCount(), 
            "Search results count mismatch");
        log.info("Dashboard test completed successfully");
    }

    @Test(dependsOnMethods = "dashboardTest", description = "Verify logout functionality")
    public void logoutTest() {
        log.info("Starting logout test");
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt(), "Login page is not displayed after logout");
        log.info("Logout test completed successfully");
    }
}
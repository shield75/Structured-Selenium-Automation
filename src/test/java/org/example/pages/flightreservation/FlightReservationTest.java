package org.example.pages.flightreservation;

import org.example.pages.AbstractTest;
import org.example.pages.flightreservation.model.FlightReservationTestData;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.JsonUtil;

public class FlightReservationTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(FlightReservationTest.class);
    private FlightReservationTestData testData;
    private static final String BASE_URL = "https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html";

    @BeforeTest
    @Parameters("testDataPath")
    public void setParameters(String testDataPath) {
        log.info("Loading test data from: {}", testDataPath);
        this.testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
    }

    @Test(description = "Verify user registration with valid data")
    public void userRegistrationTest() {
        log.info("Starting user registration test");
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(BASE_URL);
        Assert.assertTrue(registrationPage.isAt(), "Registration page is not displayed");

        registrationPage
            .enterUserDetails(testData.firstName(), testData.lastName())
            .enterUserCredentials(testData.email(), testData.password())
            .enterAddress(testData.street(), testData.city(), testData.zip())
            .register();
        log.info("User registration completed successfully");
    }

    @Test(dependsOnMethods = "userRegistrationTest", description = "Verify registration confirmation")
    public void registrationConfirmationTest() {
        log.info("Verifying registration confirmation");
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt(), "Registration confirmation page is not displayed");
        Assert.assertEquals(registrationConfirmationPage.getFirstName(), testData.firstName(), 
            "First name mismatch in confirmation");
        
        registrationConfirmationPage.goToFlightsSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest", description = "Verify flight search functionality")
    public void flightsSearchTest() {
        log.info("Performing flight search with {} passengers", testData.passengersCount());
        FlightSearchPage flightsSearchPage = new FlightSearchPage(driver);
        Assert.assertTrue(flightsSearchPage.isAt(), "Flights search page is not displayed");
        
        flightsSearchPage
            .selectPassengers(testData.passengersCount())
            .searchFlights();
    }

    @Test(dependsOnMethods = "flightsSearchTest", description = "Verify flight selection process")
    public void flightsSelectionTest() {
        log.info("Selecting and confirming flights");
        FlightSelectionPage flightsSelectionPage = new FlightSelectionPage(driver);
        Assert.assertTrue(flightsSelectionPage.isAt(), "Flights selection page is not displayed");
        
        flightsSelectionPage
            .selectFlights()
            .confirmFlights();
    }

    @Test(dependsOnMethods = "flightsSelectionTest", description = "Verify flight reservation confirmation")
    public void flightReservationConfirmationTest() {
        log.info("Verifying flight reservation confirmation");
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt(), "Flight confirmation page is not displayed");
        
        String actualPrice = flightConfirmationPage.getPrice();
        String expectedPrice = testData.expectedPrice();
        Assert.assertEquals(actualPrice, expectedPrice, 
            String.format("Price mismatch. Expected: %s, Actual: %s", expectedPrice, actualPrice));
        log.info("Flight reservation completed successfully with price: {}", actualPrice);
    }
}
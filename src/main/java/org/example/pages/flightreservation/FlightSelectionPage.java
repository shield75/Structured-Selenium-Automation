package org.example.pages.flightreservation;

import org.example.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FlightSelectionPage extends AbstractPage {

    @FindBy(name = "departure-flight")
    private List<WebElement> departureFlightOptions;

    @FindBy(name = "arrival-flight")
    private List<WebElement> arrivalFlightOptions;

    @FindBy(id = "confirm-flights")
    private WebElement confirmFlightsButton;

    public FlightSelectionPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        try {
            this.wait.until(ExpectedConditions.visibilityOf(this.confirmFlightsButton));
            return this.confirmFlightsButton.isDisplayed();
        } catch (Exception e) {
            throw new RuntimeException("Flight selection page is not loaded", e);
        }
    }

    public FlightSelectionPage selectFlights() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(departureFlightOptions));
            wait.until(ExpectedConditions.visibilityOfAllElements(arrivalFlightOptions));
            
            if (departureFlightOptions.isEmpty() || arrivalFlightOptions.isEmpty()) {
                throw new RuntimeException("No flight options available");
            }

            int random = ThreadLocalRandom.current().nextInt(0, departureFlightOptions.size());
            
            WebElement departureFlight = departureFlightOptions.get(random);
            WebElement arrivalFlight = arrivalFlightOptions.get(random);
            
            wait.until(ExpectedConditions.elementToBeClickable(departureFlight)).click();
            wait.until(ExpectedConditions.elementToBeClickable(arrivalFlight)).click();
            
            return this;
        } catch (Exception e) {
            throw new RuntimeException("Failed to select flights", e);
        }
    }

    public FlightSelectionPage confirmFlights() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(confirmFlightsButton)).click();
            return this;
        } catch (Exception e) {
            throw new RuntimeException("Failed to confirm flights", e);
        }
    }
}

package org.example.pages.flightreservation;

import org.example.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightSearchPage extends AbstractPage {

    @FindBy(id = "passengers")
    private WebElement passengerSelect;

    @FindBy(id = "search-flights")
    private WebElement searchFlightsButton;

    public FlightSearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        try {
            this.wait.until(ExpectedConditions.visibilityOf(this.passengerSelect));
            return this.passengerSelect.isDisplayed();
        } catch (Exception e) {
            throw new RuntimeException("Flight search page is not loaded", e);
        }
    }

    public FlightSearchPage selectPassengers(String noOfPassengers) {
        try {
            this.wait.until(ExpectedConditions.elementToBeClickable(passengerSelect));
            Select passengers = new Select(this.passengerSelect);
            passengers.selectByValue(noOfPassengers);
            return this;
        } catch (Exception e) {
            throw new RuntimeException("Failed to select passengers: " + noOfPassengers, e);
        }
    }

    public FlightSearchPage searchFlights() {
        try {
            this.wait.until(ExpectedConditions.elementToBeClickable(searchFlightsButton));
            this.searchFlightsButton.click();
            return this;
        } catch (Exception e) {
            throw new RuntimeException("Failed to click search flights button", e);
        }
    }
}

package org.example.pages.flightreservation;

import org.example.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Page Object representing the flight confirmation page
 */
public class FlightConfirmationPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(1) .col:nth-child(2)")
    private WebElement flightConfirmationElement;

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2)")
    private WebElement totalPriceElement;

    public FlightConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.flightConfirmationElement));
        return this.flightConfirmationElement.isDisplayed();
    }

    /**
     * Gets the flight confirmation number
     * @return the confirmation number as a string
     */
    public String getConfirmationNumber() {
        String confirmation = this.flightConfirmationElement.getText();
        log.info("Flight confirmation# : {}", confirmation);
        return confirmation;
    }

    /**
     * Gets the total price of the flight
     * @return the total price as a string
     */
    public String getPrice() {
        String price = this.totalPriceElement.getText();
        log.info("Total price : {}", price);
        return price;
    }
}
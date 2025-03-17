package org.example.pages.flightreservation;
import org.example.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class RegistrationPage extends AbstractPage {
    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(name = "street")
    private WebElement streetInput;

    @FindBy(name = "city")
    private WebElement cityInput;

    @FindBy(name = "zip")
    private WebElement zipInput;

    @FindBy(id = "register-btn")
    private WebElement registerButton;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        try {
            this.wait.until(ExpectedConditions.visibilityOf(this.firstNameInput));
            return this.firstNameInput.isDisplayed();
        } catch (Exception e) {
            throw new RuntimeException("Failed to verify page visibility", e);
        }
    }

    public RegistrationPage goTo(String url) {
        try {
            this.driver.get(url);
            return this;
        } catch (Exception e) {
            throw new RuntimeException("Failed to navigate to registration page", e);
        }
    }

    public RegistrationPage enterUserDetails(String firstName, String lastName) {
        try {
            waitForElement(firstNameInput);
            this.firstNameInput.sendKeys(firstName);
            this.lastNameInput.sendKeys(lastName);
            return this;
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter user details", e);
        }
    }

    public RegistrationPage enterUserCredentials(String email, String password) {
        try {
            waitForElement(emailInput);
            this.emailInput.sendKeys(email);
            this.passwordInput.sendKeys(password);
            return this;
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter user credentials", e);
        }
    }

    public RegistrationPage enterAddress(String street, String city, String zip) {
        try {
            waitForElement(streetInput);
            this.streetInput.sendKeys(street);
            this.cityInput.sendKeys(city);
            this.zipInput.sendKeys(zip);
            return this;
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter address", e);
        }
    }

    public void register() {
        try {
            waitForElement(registerButton);
            this.registerButton.click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to complete registration", e);
        }
    }

    private void waitForElement(WebElement element) {
        this.wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}

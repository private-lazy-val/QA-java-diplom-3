package com.PO;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;

public class RegisterPage {

    @FindBy(how = How.XPATH, using = ".//fieldset[1]/div/div/input")
    private SelenideElement nameField;

    @FindBy(how = How.XPATH, using = ".//fieldset[2]/div/div/input")
    private SelenideElement emailField;

    @FindBy(how = How.XPATH, using = ".//fieldset[3]/div/div/input")
    private SelenideElement passwordField;

    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement logInButton;

    @FindBy(how = How.XPATH, using = ".//p[text()='Некорректный пароль']")
    private SelenideElement invalidPasswordErrorMessage;


    public void fillName(String name) {
        nameField.setValue(name);
    }

    public void fillEmail(String email) {
        emailField.setValue(email);
    }

    public void fillPassword(String password) {
        passwordField.setValue(password);
    }

    public void clickRegisterButton() {
        registerButton.click();
    }

    public void registerNewUser(String name, String email, String password) {
        fillName(name);
        fillEmail(email);
        fillPassword(password);
        clickRegisterButton();
    }

    public void clickLogInButton() {
        logInButton.click();
    }

    public void checkInvalidPasswordErrorMessageIsVisible() {
        invalidPasswordErrorMessage.shouldBe(visible);
    }
}

package com.PO;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

    public static final String URL = "https://stellarburgers.nomoreparties.site/login";

    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    private SelenideElement emailField;

    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    private SelenideElement passwordField;

    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private SelenideElement loginButton;

    @FindBy(how = How.XPATH, using = ".//div/main/div/div/p[1]/a")
    private SelenideElement registerButton;

    @FindBy(how = How.XPATH, using = ".//a[text()='Восстановить пароль']")
    private SelenideElement forgotPasswordButton;

    public void fillEmail(String email) {
        emailField.setValue(email);
    }

    public void fillPassword(String password) {
        passwordField.setValue(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void loginUser(String email, String password) {
        fillEmail(email);
        fillPassword(password);
        clickLoginButton();
    }

    public void clickRegisterButton() {
        registerButton.click();
    }

    public void clickForgotPasswordButton() {
        forgotPasswordButton.click();
    }
}

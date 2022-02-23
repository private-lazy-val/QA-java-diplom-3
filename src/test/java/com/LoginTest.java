package com;

import com.PO.ForgotPasswordPage;
import com.PO.LoginPage;
import com.PO.MainPage;
import com.PO.RegisterPage;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@RunWith(Parameterized.class)
public class LoginTest {
    static MainPage mainPage;

    private final UserOperations userOperations = new UserOperations();

    private final String testName;
    private final String pathToDriver;

    private String email;
    private String password;

    AllureLifecycle oLifecycle = Allure.getLifecycle();

    public LoginTest(String testName, String pathToDriver) {
        this.testName = testName;
        this.pathToDriver = pathToDriver;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"Yandex driver", "/Users/valeriyalondonenko/Downloads/yandexdriver"},
                {"Chrome driver", "/Users/valeriyalondonenko/Downloads/chromedriver"}
        };
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", pathToDriver);
        mainPage = open(MainPage.URL, MainPage.class);

        Map<String, String> userData = userOperations.register();
        email = userData.get("email");
        password = userData.get("password");
    }


    @After
    public void tearDown() {
        getWebDriver().quit();

        userOperations.delete();
    }

    @Test
    @Description("Checking login by clicking on 'Log In' button on Main page")
    public void loginViaLogInButtonUserLoggedIn() {
        oLifecycle.updateTestCase(testResult -> testResult.setName("Check login via Log In button with " + testName));

        mainPage.clickLogInButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.loginUser(email, password);

        mainPage.getCreateOrderButton().shouldBe(visible);
    }

    @Test
    @Description("Checking login by clicking on 'Account profile' button on Main page")
    public void loginViaAccountProfileButtonUserLoggedIn() {
        oLifecycle.updateTestCase(testResult -> testResult.setName("Check login via Account Profile button with " + testName));

        mainPage.clickProfileButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.loginUser(email, password);

        mainPage.getCreateOrderButton().shouldBe(visible);
    }

    @Test
    @Description("Checking login by clicking on 'Log In' button on Registration page")
    public void loginViaRegistrationPageUserLoggedIn() {
        oLifecycle.updateTestCase(testResult -> testResult.setName("Check login from Registration page with " + testName));

        mainPage.clickLogInButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegisterButton();
        RegisterPage registerPage = page(RegisterPage.class);
        registerPage.clickLogInButton();
        loginPage.loginUser(email, password);

        mainPage.getCreateOrderButton().shouldBe(visible);
    }

    @Test
    @Description("Checking login by clicking on 'Log In' button on Forgot Password page")
    public void loginViaRecoverPasswordPageUserLoggedIn() {
        oLifecycle.updateTestCase(testResult -> testResult.setName("Check login from Forgot Password Page with " + testName));

        mainPage.clickLogInButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickForgotPasswordButton();
        ForgotPasswordPage forgotPassword = page(ForgotPasswordPage.class);
        forgotPassword.clickLogInButton();
        loginPage.loginUser(email, password);

        mainPage.getCreateOrderButton().shouldBe(visible);
    }
}